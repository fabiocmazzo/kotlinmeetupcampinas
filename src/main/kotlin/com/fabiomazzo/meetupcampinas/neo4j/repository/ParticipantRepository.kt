package com.fabiomazzo.meetupcampinas.neo4j.repository

import com.fabiomazzo.meetupcampinas.neo4j.model.Participant
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

/**
 * Parcipant Neo4J Repository.
 * Created by fabiomazzo on 02/07/2017.
 */
@Repository
interface ParticipantRepository : Neo4jRepository<Participant, Long> {

    fun findByCode(code: String): Participant

}
