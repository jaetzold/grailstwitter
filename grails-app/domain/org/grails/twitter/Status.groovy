package org.grails.twitter

import org.grails.twitter.auth.Person

class Status implements Serializable {
    private static final long serialVersionUID = 1L

    String message
    Person author
    Date dateCreated
    long ttl

    static hasMany = [tags: Tag]
}
