package org.grails.twitter

import grails.plugin.cache.Cacheable
import grails.plugin.cache.CacheEvict

import org.grails.twitter.auth.Person

class StatusService {

    static rabbitQueue = "grailstwitter.status"

    def grailsCacheManager
    def springSecurityService

    void handleMessage(String username) {
        log.debug "Message received. New status message posted by user <${username}>."
        def following = Person.where { followed.username == username }.property("id").list()

        // TODO clear cache for everyone following given user
        for (followerId in following) {
            grailsCacheManager.getCache("timeline").evict(followerId)
        }

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
