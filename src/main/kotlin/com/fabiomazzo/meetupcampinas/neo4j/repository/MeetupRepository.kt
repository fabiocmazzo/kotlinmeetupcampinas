package com.fabiomazzo.meetupcampinas.neo4j.repository

import com.fabiomazzo.meetupcampinas.neo4j.model.Meetup
import org.springframework.data.neo4j.annotation.Depth
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

/**
 * Meetup Repository.
 *
 * @author Fabio Covolo Mazzo
 */

@Repository
interface MeetupRepository : Neo4jRepository<Meetup, Long> {

    fun findByCode(code: String, @Depth depth: Int): Meetup

}