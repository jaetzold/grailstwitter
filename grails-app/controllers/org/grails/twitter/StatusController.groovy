package org.grails.twitter

import grails.plugins.springsecurity.Secured
import grails.plugin.cache.Cacheable

import org.grails.twitter.auth.Person

@Secured('IS_AUTHENTICATED_FULLY')
class StatusController {

    def springSecurityService
    def statusService

    def index() {
        def messages = statusService.currentUserTimeline(currentUserId)
        [statusMessages: messages]
    }

    def updateStatus(StatusCommand cmd) {
        if (cmd.hasErrors()) {
            render status: 400, text: "Message does not meet constraints: ${g.message(error: cmd.errors.getFieldError('message'))}"
            return
        }

        statusService.updateStatus currentUserId, cmd.message
        def messages = statusService.currentUserTimeline(currentUserId)
        render template: 'statusMessages', collection: messages, var: 'statusMessage'
    }

    def follow() {
        statusService.follow params.long("id")
        redirect action: 'index'
    }

    private getCurrentUserId() {
        springSecurityService.principal.id
    }
}

class StatusCommand {
    String message

    static constraints = {
        message blank: false, nullable: false, maxSize: 50
    }
}
