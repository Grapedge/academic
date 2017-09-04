package me.maxct.academic.entity

import java.io.Serializable

/**
 * 课程ID, 根据名字和学院确定
 * @param courseName 课程名字
 * @param academy 学院id
 * @param semester 学期id
 * @param teacher 教师用户名
 * @author imaxct
 */
data class CourseId(
    val courseName: String? = null,
    val academy: Academy? = null,
    val semester: Semester? = null,
    val teacher: User? = null
) : Serializable {
    private constructor() : this(courseName = null)

    /*override fun equals(other: Any?): Boolean {
        val o = other as CourseId
        return this.courseName.equals(o.courseName) && this.academy?.id == o.academy?.id
    }

    override fun hashCode(): Int {
        var result = courseName?.hashCode() ?: 0
        result = 31 * result + (academy?.hashCode() ?: 0)
        return result
    }*/
}