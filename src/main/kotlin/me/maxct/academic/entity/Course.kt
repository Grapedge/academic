package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.*

/**
 * @param courseName 课程名
 * @param teacher 教师
 * @param credit 学分
 * @param week 上课周次
 * @param total 课程容量
 * @param remaining 课余量
 */
@Entity
@Table(name = "ACA_course")
data class Course(

    @Id @GeneratedValue
    val id: Long? = null,

    @Column(length = 40)
    val courseName: String? = null,

    @ManyToOne
    @JoinColumn
    val academy: Academy? = null,

    @ManyToOne
    @JoinColumn
    val semester: Semester? = null,

    @ManyToOne
    @JoinColumn
    val teacher: User? = null,

    val credit: Double = 0.0,

    @Column(length = 24)
    val week: String? = null,

    val total: Int = 0,

    val remaining: Int = 0,

    val courseOrder: Int = 1,
    val day: Int = 1
) : Serializable {
    private constructor() : this(credit = 0.0)

    /*override fun equals(other: Any?): Boolean {
        return this.hashCode() == other?.hashCode()
    }

    override fun hashCode(): Int {
        return id!!.hashCode()
    }*/

    /*fun getId(): CourseId = CourseId(courseName, academy, semester, teacher)*/
}