package com.fabiomazzo.meetupcampinas

import org.jboss.logging.Logger
import org.springframework.beans.factory.InjectionPoint
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct


/**
 * Factories for Spring beans used in this project.
 *
 * @author Fabio Covolo Mazzo
 */
@Configuration
@ComponentScan
@EnableCaching
open class MeetupConfiguration {

   @Bean
    open fun getLogger(p: InjectionPoint): Logger {
        return Logger.getLogger(p.javaClass.canonicalName)
    }

    fun createTestData() {

    }

    @PostConstruct
    fun init() {
        createTestData();
    }
}