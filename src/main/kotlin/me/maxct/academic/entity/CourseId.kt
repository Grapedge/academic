package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

/**
 * Created by imaxct on 17-8-1.
 * academic
 */
@Embeddable
data class CourseId(
        @Column(length = 32)
        val name: String? = null,
        @ManyToOne
        @JoinColumn
        val academy: Academy? = null
) : Serializable {
    private constructor(): this(null, null)
}