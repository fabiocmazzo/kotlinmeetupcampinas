package com.fabiomazzo.meetupcampinas.web.rest

import com.fabiomazzo.meetupcampinas.neo4j.model.Participant
import com.fabiomazzo.meetupcampinas.neo4j.repository.ParticipantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

/**
 * RestController
 *
 * @author Fabio Covolo Mazzo
 */

@RestController
class ParticipantRestController {

    @Autowired
    lateinit var participantRepository: ParticipantRepository

   @GetMapping("/participants")
    fun participants(): Flux<Participant> {
        val dialogSpecificationFlux = Flux.fromIterable(participantRepository.findAll())
        return dialogSpecificationFlux
    }

}
