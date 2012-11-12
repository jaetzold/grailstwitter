grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()
        mavenRepo "http://dist.gemstone.com/maven/release"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
//        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"

        // runtime 'mysql:mysql-connector-java:5.1.5'
    }
    plugins {
        compile ":cache:1.0.1",
                ":cache-ehcache:1.0.0",
                ":events-push:1.0.M3",
                ":hibernate:$grailsVersion",
                ":platform-core:1.0.M6.1",
                ":rabbitmq:1.0.0.BUILD-SNAPSHOT",
                ":searchable:0.6.4",
                ":spring-security-core:1.2.7.3", {
            excludes "functional-test", "resources"
        }

        runtime ":jquery:1.8.0",
                ":database-migration:1.2",
                ":resources:1.2.RC2",
                ":cached-resources:1.1",
                ":zipped-resources:1.0.1"

        test ":spock:0.7", {
            excludes "spock-grails-support"
        }

        build ":tomcat:$grailsVersion"
    }
}

grails.tomcat.nio = true
//grails.project.fork.run = true
