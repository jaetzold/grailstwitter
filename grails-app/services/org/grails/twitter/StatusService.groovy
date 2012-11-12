package org.grails.twitter

import grails.events.Listener
import grails.plugin.cache.Cacheable
import grails.plugin.cache.CacheEvict

import org.grails.twitter.auth.Person

class StatusService {

    def grailsCacheManager
    def groovyPageRenderer
    def springSecurityService

    @Listener(namespace="gorm")
    void afterInsert(Status status) {
        Status.withNewSession {
            status = Status.get(status.id)

            log.debug "Message received. New status message posted by user <${status.authorId}>."
            def following = Person.where { followed.id == status.authorId }.property("id").list()

            // TODO clear cache for everyone following given user
            for (followerId in following) {
                grailsCacheManager.getCache("timeline").evict(followerId)
                println "Here!!!!"
                event topic: "statusAdded", data: [userId: followerId, content: groovyPageRenderer.render(
                        template: "/status/statusMessages",
                        model: [statusMessage: status]) ]
            }

        }

    }

    @Listener
    void statusAdded(Map data) {
        println ">> Status message added. Notifying user ${data.userId} for content: ${data.content}"
    }

    @CacheEvict(value="timeline", key="#userId")
    void updateStatus(long userId, String message) {
        def status = new Status(message: message)
        status.author = Person.get(userId)
        status.save()
    }

    void follow(long personId) {
        def person = Person.get(personId)
        if (person) {
            def currentUser = lookupPerson()
            currentUser.addToFollowed(person)

            grailsCacheManager.getCache("timeline").evict(currentUser.id)
        }
    }

    @Cacheable(value="timeline", key="#userId")
    def currentUserTimeline(long userId) {
        log.debug "No messages found in cache for user with ID <${userId}>. Querying database..."
        def per = Person.get(userId)

        def messages = Status.whereAny {
            author.username == per.username 
            if (per.followed) author in per.followed
        }.list(max: 10, sort: "dateCreated", order: "desc")

        return messages
    }

    private lookupPerson() {
        Person.get(springSecurityService.principal.id)
    }
}
