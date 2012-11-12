package org.grails.twitter

class Tag {
    String name

    static belongsTo = [status: Status]

    static constraints = {
        name blank: false
    }
}
