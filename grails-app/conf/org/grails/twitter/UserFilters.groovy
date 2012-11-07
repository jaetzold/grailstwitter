package org.grails.twitter

import org.grails.twitter.auth.Person

class UserFilters {

    def springSecurityService

    def filters = {
        all(controller:'*', action:'*') {
            before = {

            }
            after = { Map model ->
                if (model == null) model = [:]

                if (!(springSecurityService.principal instanceof CharSequence)) {
                    model["currentUser"] = Person.get(springSecurityService.principal.id)
                }

                return model
            }
            afterView = { Exception e ->

            }
        }
    }
}
