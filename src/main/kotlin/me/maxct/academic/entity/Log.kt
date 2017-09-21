package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by imaxct on 17-9-21.
 */
@Entity
@Table(name = "logging_event")
data class Log(

    @Id
    @Column(name = "event_id")
    val eventId: Long? = null,

    @Column(name = "timestmp")
    val timestmp: Long? = null,

    @Column(name = "formatted_message")
    val formattedMessage: String? = null
) : Serializable {
    private constructor() : this(eventId = null)
}