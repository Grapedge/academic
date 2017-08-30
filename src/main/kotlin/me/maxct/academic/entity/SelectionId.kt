package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.Embeddable
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

/**
 * @author imaxct
 * @param course 课程id(包含了课程名,开设学院,学期)
 * @param user 用户
 */
@Embeddable
data class SelectionId(

    @ManyToOne
    @JoinColumn
    val course: Course? = null,

    @ManyToOne
    @JoinColumn
    val user: User? = null
) : Serializable {
    private constructor() : this(user = null)

    override fun equals(other: Any?): Boolean {
        val o = other as SelectionId
        return this.course?.id == o.course?.id && this.user?.id == o.user?.id
    }

    override fun hashCode(): Int {
        var result = course?.hashCode() ?: 0
        result = 31 * result + (user?.hashCode() ?: 0)
        return result
    }
}