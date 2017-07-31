package me.maxct.academic.entity

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

/**
 * Created by imaxct on 17-7-31.
 * academic
 */
@Entity
@Table(name = "ACA_record")
data class Record(
        @Id
        @GeneratedValue
        val id: Long? = null,
        @ManyToOne
        @JoinColumn
        val user: User? = null,
        @ManyToOne
        @JoinColumn
        val performer: User? = null,
        @Column(name = "record_time")
        val timestamp: LocalDate? = null,
        val reward: Boolean = false,
        @Column(length = 500)
        val description: String? = null
): Serializable {
    private constructor(): this(null, null, null, null, false, null)
}