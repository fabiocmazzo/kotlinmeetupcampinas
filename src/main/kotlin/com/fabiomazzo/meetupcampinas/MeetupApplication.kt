package com.fabiomazzo.meetupcampinas

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories

/**
 * Meetup Application Main SpringBoot Class.
 * @author Fabio Covolo Mazzo
 */
@SpringBootApplication
@EntityScan("com.fabiomazzo.meetupcampinas.neo4j.model")
@EnableAutoConfiguration(exclude = arrayOf(DataSourceAutoConfiguration::class))
@EnableNeo4jRepositories
open class MeetupApplication {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(com.fabiomazzo.meetupcampinas.MeetupApplication::class.java, *args)
        }
    }
}


