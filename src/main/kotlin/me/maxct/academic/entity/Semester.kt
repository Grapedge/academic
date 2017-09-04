package me.maxct.academic.entity

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

/**
 * 学期
 * @param id id
 * @param name courseName
 * @param beginDate 学期开始时间
 * @param endDate 学期结束时间
 */
@Entity
@Table(name = "ACA_semester")
data class Semester(
    @Id
    @GeneratedValue
    val id: Long? = null,

    @Column(length = 12)
    val name: String = "",


    val beginDate: LocalDate? = null,

    val endDate: LocalDate? = null
) : Serializable {
    private constructor(): this(name = "")

    /*override fun equals(other: Any?): Boolean {
        return this.hashCode() == other?.hashCode()
    }

    override fun hashCode(): Int {
        return id!!.hashCode()
    }*/
}