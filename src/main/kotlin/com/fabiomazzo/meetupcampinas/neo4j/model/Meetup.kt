package com.fabiomazzo.meetupcampinas.neo4j.model

import org.neo4j.ogm.annotation.GraphId
import org.neo4j.ogm.annotation.Index
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

import java.io.Serializable

/**
 * The Class Meetup.

 * @author Fabio Covolo Mazzo
 */
@NodeEntity
data class Meetup(@GraphId
                  var id: Long?,
                  @Index(unique = true)
                  var code: String = "",
                  @Relationship(type = "HAVE_PARTICIPANTS")
                  var enrollementList: MutableSet<Enrollment> = hashSetOf()) : Serializable {

    /**
     * Neo4j OGM precisa de um construtor público sem argumentos
     */
    constructor() : this(id = null) {
    }



}
