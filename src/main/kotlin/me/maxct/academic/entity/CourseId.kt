package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

/**
 * 课程ID, 根据名字和学院确定
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
    private constructor() : this(null, null)

    override fun equals(other: Any?): Boolean {
        val o = other as CourseId
        return this.name.equals(o.name) && this.academy?.id == o.academy?.id
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (academy?.hashCode() ?: 0)
        return result
    }
}