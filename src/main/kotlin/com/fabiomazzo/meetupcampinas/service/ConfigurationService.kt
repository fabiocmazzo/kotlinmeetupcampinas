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

    fun createTestData() {
        var meetup = Meetup()
        meetup.code = RandomStringUtils.random(5)
        meetup = meetupRepository.save(meetup)

        for(i in 0..40) {
            var participant = Participant()
            participant.code = RandomStringUtils.random(5)
            participant.name = RandomStringUtils.random(10)
            participant.nickname = RandomStringUtils.random(6)
            participant.addMeetup(meetup)
            participant = participantRepository.save(participant)
            meetup.participantList.add(participant)
        }

        meetup = meetupRepository.save(meetup)

    }

    @PostConstruct
    fun init() {
        createTestData()
    }
}
