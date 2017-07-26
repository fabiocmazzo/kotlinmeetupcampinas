package com.fabiomazzo.meetupcampinas.service


import com.fabiomazzo.meetupcampinas.neo4j.model.Meetup
import com.fabiomazzo.meetupcampinas.neo4j.model.Participant
import com.fabiomazzo.meetupcampinas.neo4j.repository.MeetupRepository
import com.fabiomazzo.meetupcampinas.neo4j.repository.ParticipantRepository
import org.apache.commons.lang3.RandomStringUtils
import org.jboss.logging.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

/**
 * Configuration Service, used as Startup Bean.
 *
 * @author Fabio Covolo Mazzo
 */
@Service
class ConfigurationService {

    @Autowired
    lateinit var logger: Logger

    @Autowired
    lateinit var meetupRepository: MeetupRepository

    @Autowired
    lateinit var participantRepository: ParticipantRepository


    /**
     *  Apenas para exemplificar function como parametro
     */
    private fun runCreateDate(function: () -> Unit) {
        function.invoke()
    }

    fun createTestData() {

        runCreateDate {


            val meetupsCodes = mutableListOf<String>()

            for (i in 0..10) {
                var meetup = Meetup()
                meetup.code = RandomStringUtils.randomAlphabetic(5)
                meetup = meetupRepository.save(meetup)
                meetupsCodes.add(meetup.code)
            }



            for (i in 0..40) {
                var participant = Participant()
                participant.code = RandomStringUtils.randomAlphabetic(5)
                participant.name = RandomStringUtils.randomAlphabetic(10)
                participant.nickname = RandomStringUtils.randomAlphabetic(6)
                var meetup = meetupRepository.findByCode(meetupsCodes.first(),0)
                participant.enroll(meetup)
                participantRepository.save(participant)
                meetupsCodes.sort()
            }

        }


    }


    @PostConstruct
    fun init() {
        createTestData()
    }
}
