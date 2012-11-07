package org.grails.twitter

import org.grails.twitter.auth.Person

class Status {
    String message
    Person author
    Date dateCreated

    transient jmsService
    transient afterInsert = {
        rabbitSend 'grailstwitter.status', author.username
    }
}
