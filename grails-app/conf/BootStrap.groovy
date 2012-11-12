import org.grails.twitter.Status
import org.grails.twitter.auth.Authority
import org.grails.twitter.auth.Person
import org.grails.twitter.auth.PersonAuthority

class BootStrap {

    def searchableService
    def springSecurityService

    def init = { servletContext ->
        if (!Person.count()) {
            createData()
        }

        // We manually start the mirroring process to ensure that it comes after
        // Autobase performs its migrations.
        println "Performing bulk index"
        searchableService.reindex()

        println "Starting mirror service"
        searchableService.startMirroring()
    }

    def destroy = {
    }

    private void createData() {
        def userRole = new Authority(authority: 'ROLE_USER').save()

        String password = springSecurityService.encodePassword('password')

        [jeff: 'Jeff Brown', graeme: 'Graeme Rocher', burt: 'Burt Beckwith', peter: 'Peter Ledbrook'].each { userName, realName ->
            def user = new Person(username: userName, realName: realName, password: password, enabled: true).save()
            PersonAuthority.create user, userRole, true

            new Status(message: "Having a BBQ!", author: user).save()
            new Status(message: "Hacking on Grails", author: user).save()
            new Status(message: "Drinking Mojitos in Mauritius", author: user).save()
        }
    }
}
