package com.fabiomazzo.meetupcampinas.service


import com.fabiomazzo.meetupcampinas.neo4j.model.Meetup
import com.fabiomazzo.meetupcampinas.neo4j.model.Participant
import com.fabiomazzo.meetupcampinas.neo4j.repository.MeetupRepository
import com.fabiomazzo.meetupcampinas.neo4j.repository.ParticipantRepository
import org.apache.commons.lang3.RandomStringUtils
import org.jboss.logging.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
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
            for (i in 0..20) {
                var meetup = Meetup()
                meetup.code = RandomStringUtils.randomAlphabetic(5)
                meetupRepository.save(meetup)
                meetupsCodes.add(meetup.code)
            }
            val randomGenerator: Random = Random()
            for (i in 0..1000) {
                var participant = Participant()
                participant.code = RandomStringUtils.randomAlphabetic(5)
                participant.name = RandomStringUtils.randomAlphabetic(10)
                participant.nickname = RandomStringUtils.randomAlphabetic(6)
                var meetup = meetupRepository.findByCode(meetupsCodes.get(randomGenerator.nextInt(meetupsCodes.size)),0)
                participant.enroll(meetup)
                participantRepository.save(participant)
            }
        }
    }

    @PostConstruct
    fun init() {
        createTestData()
    }
}
