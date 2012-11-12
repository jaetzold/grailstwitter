package org.grails.twitter

import grails.test.mixin.*
import org.grails.twitter.auth.Person
import spock.lang.*


/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(StatusController)
@Mock(Status)
class StatusControllerSpec extends Specification {

    void "Index page"() {
        given: "A mock statusService"
        def statusService = Mock(StatusService)
        controller.statusService = statusService

        when: "The index action is executed"
        def model = controller.index()

        then: "The index view is rendered"
        statusService.currentUserTimeline() >> [new Status(message: "Hello"), new Status(message: "world")]
        model["statusMessages"]*.message == ["Hello", "world"]

    }

    void "Update status"() {
        given: "An update message from a particular user"
        def cmd = new StatusCommand(message: "Testing update status")
        cmd.validate()

        and: "a mock statusService"
        def statusService = Mock(StatusService)
        controller.statusService = statusService

        when: "The updateStatus action is called with that message"
        controller.updateStatus(cmd)

        then: "The message is returned as markup"
        statusService.currentUserTimeline() >> [
                new Status(message: "Testing update status", author: new Person(realName: "Peter Ledbrook")),
                new Status(message: "Hello", author: new Person(realName: "Jeff Brown")),
                new Status(message: "world", author: new Person(realName: "Peter Ledbrook"))]
        response.text.contains("Testing update status")
        response.text.contains("Hello<br/>")
        response.text.contains("world<br/>")
    }

    void "Update status with invalid message"() {
        given: "An update message from a particular user"
        def cmd = new StatusCommand(message: " ")
        cmd.validate()

        and: "a mock statusService"
        def statusService = Mock(StatusService)
        controller.statusService = statusService

        when: "The updateStatus action is called with that message"
        controller.updateStatus(cmd)

        then: "The response is a 404"
        response.status == 400
        response.text.contains("Message does not meet constraints:")
    }

    void "Follow a user"() {
        given: "A user ID to follow"
        params.id = "2"

        and: "a mock statusService"
        def statusService = Mock(StatusService)
        controller.statusService = statusService

        when: "The follow action is called"
        controller.follow()

        then: "The page redirects to the index page after passing the follow information to the status service"
        1 * statusService.follow(2)
        response.redirectedUrl == "/status/index"
    }
}
