package com.fabiomazzo.meetupcampinas.neo4j.model

import org.neo4j.ogm.annotation.EndNode
import org.neo4j.ogm.annotation.GraphId
import org.neo4j.ogm.annotation.RelationshipEntity
import org.neo4j.ogm.annotation.StartNode

@RelationshipEntity(type = "SCHEDULED")
data class NextMeetup(@GraphId
                      var id: Long?) {


    /**
     * Neo4j OGM precisa de um construtor público sem argumentos
     */
    constructor() : this(id = null) {
    }

    @StartNode
    var previousMeetup: Meetup = Meetup()

    @EndNode
    var nextMeetup: Meetup = Meetup()


}
