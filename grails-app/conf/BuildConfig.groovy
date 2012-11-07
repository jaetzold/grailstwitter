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
        compile "org.springframework.amqp:spring-rabbit:1.1.2.RELEASE"

        // runtime 'mysql:mysql-connector-java:5.1.5'
    }
    plugins {
        compile ":hibernate:2.1.1",
                ":gemfire:1.0.0.M5",
                ":rabbitmq:1.0.0.RC2",
                ":searchable:0.6.4",
                ":spring-security-core:1.2.7.3", {
            excludes "spring-rabbit"
        }

        runtime ":jquery:1.8.0"

        build ":tomcat:2.1.1"
    }
}
