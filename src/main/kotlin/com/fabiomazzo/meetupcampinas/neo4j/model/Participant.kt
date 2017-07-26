package com.fabiomazzo.meetupcampinas.neo4j.model

import org.neo4j.ogm.annotation.GraphId
import org.neo4j.ogm.annotation.Index
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import java.io.Serializable
import java.util.*

/**
 * Classe que representa o node Intent
 *
 * @author Fabio Covolo Mazzo
 */
@NodeEntity
data class Participant(@GraphId var id: Long?,
                       var name: String = "",
                       @Index(unique = true)
                       var code: String = "",
                       var nickname: String = "",
                       @Relationship(type = "ENROLLED")
                       var enrollementList: MutableSet<Enrollment> = hashSetOf()) : Serializable {


    /**
     * Neo4j OGM needs a no-arg public constructor.
     */
    constructor() : this(null) {
    }

    /**
     *adiciona um Meetup.
     */
    fun enroll(meetup: Meetup) {
        var enrollment = Enrollment()
        enrollment.enrolledDate = Date()
        enrollment.meetup = meetup
        enrollment.participant = this
        this.enrollementList.add(enrollment)
        meetup.enrollementList.add(enrollment)
    }

}
