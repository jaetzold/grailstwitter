package org.grails.twitter

import grails.test.mixin.*
import org.grails.twitter.auth.Person
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(StatusService)
@Mock([Status, Person])
class StatusServiceSpec extends Specification {

    void "Updating a user's status"() {
        given: "A status service"
        def springSecurityService = GroovyMock(Map)
        def twitterCache = GroovyMock(Map)
        def service = new StatusService(springSecurityService: springSecurityService, twitterCache: twitterCache)

        and: "An existing user with ID 2"
        def author = new Person(username: "pedro", realName: "Richard Dawkins").save(validate: false)

        when: "The updateStatus() method is called with a message"
        service.updateStatus("Testing 1, 2, 3")

        then: "A Status instance is saved"
        springSecurityService.getPrincipal() >> [id: author.id]
        Status.list()*.message == ["Testing 1, 2, 3"]
    }

    void "Handle new status message"() {
        given: "A status service"
        def springSecurityService = GroovyMock(Map)
        def twitterCache = GroovyMock(Map)
        def service = new StatusService(springSecurityService: springSecurityService, twitterCache: twitterCache)

        and: "some users"
        def jeff = new Person(username: "jeff", realName: "Jeff Brown").save(validate: false)
        def peter = new Person(username: "peter")
        peter.addToFollowed(jeff)
        peter.save(validate: false)

        def burt = new Person(username: "burt").save(validate: false)
        burt.addToFollowed(jeff)
        burt.save(validate: false)

        def graeme = new Person(username: "graeme").save(validate: false)

        when: "A message is received"
        service.handleMessage("jeff")

        then: "Nothing"
        1 * twitterCache.remove("peter")
        1 * twitterCache.remove("burt")
    }
}
