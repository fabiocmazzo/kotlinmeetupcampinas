package com.fabiomazzo.meetupcampinas.neo4j.model

import org.neo4j.ogm.annotation.GraphId
import org.neo4j.ogm.annotation.Index
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import java.io.Serializable

/**
 * Classe que representa o node Intent
 *
 * @author Fabio Covolo Mazzo
 */
@NodeEntity
data class Participant(@GraphId var id: Long?,
                        var name: String = "",
                       @Index(unique = true)
                        var code : String = "",
                        var nickname: String = "",
                        @Relationship(type = "HAS_BEEN_REGISTERED_IN", direction = Relationship.UNDIRECTED)
                        var meetupList: MutableList<Meetup> = mutableListOf()) : Serializable {


    /**
     * Neo4j OGM needs a no-arg public constructor.
     */
    constructor() : this(null) {
    }

    /**
     *adiciona um Meetup.
     */
    fun addMeetup(meetup: Meetup) {
        meetupList.add(meetup)
    }

}
