package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.*

/**
 * @author imaxct
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

    /*override fun equals(other: Any?): Boolean {
        val o = other as SelectionId
        return this.course?.id == o.course?.id && this.user?.username == o.user?.username
    }

    override fun hashCode(): Int {
        var result = course?.hashCode() ?: 0
        result = 31 * result + (user?.hashCode() ?: 0)
        return result
    }*/

}