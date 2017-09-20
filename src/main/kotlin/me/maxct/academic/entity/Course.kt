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

    val credit: Double? = null,

    @Column(length = 24)
    var week: String? = null,
    val total: Int? = null,
    val remaining: Int? = null,
    val courseOrder: Int? = null,
    val day: Int? = null,
    var flag: Int? = null,
    val location: String? = null
) : Serializable {
    private constructor() : this(credit = null)

    /*override fun equals(other: Any?): Boolean {
        return this.hashCode() == other?.hashCode()
    }

    override fun hashCode(): Int {
        return id!!.hashCode()
    }*/

    /*fun getId(): CourseId = CourseId(courseName, academy, semester, teacher)*/
}