package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

/**
 * Created by imaxct on 17-8-3.
 * academic
 */
@Embeddable
data class SelectionId(
    @ManyToOne
    @JoinColumn
    val course: Course? = null,
    @ManyToOne
    @JoinColumn
    val user: User? = null,
    @Column(length = 20)
    val semester: String? = null
) : Serializable {
    private constructor() : this(semester = "2016-2017-2")

    override fun equals(other: Any?): Boolean {
        val o = other as SelectionId
        return this.course?.id == o.course?.id
            && this.user?.id == o.user?.id
            && this.semester?.equals(o.semester) as Boolean
    }

    override fun hashCode(): Int {
        var result = course?.hashCode() ?: 0
        result = 31 * result + (user?.hashCode() ?: 0)
        result = 31 * result + (semester?.hashCode() ?: 0)
        return result
    }
}