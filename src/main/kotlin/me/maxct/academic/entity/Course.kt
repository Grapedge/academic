package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.*

/**
 * @param id 课程的复合id
 * @param teacher 教师
 * @param credit 学分
 * @param week 上课周次
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
//TODO todo
) : Serializable {
    private constructor() : this(credit = 0.0)
}