package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.*

/**
 * Created by imaxct on 17-8-1.
 * academic
 */
@Entity
@Table(name = "ACA_course")
data class Course(
        @EmbeddedId
        val id: CourseId? = null,
        @ManyToOne
        @JoinColumn
        val teacher: User? = null,
        val credit: Double = 0.0,
        @Column(length = 24)
        val week: String? = null
) : Serializable