package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.*

/**
 * 学院
 * @param id 学院id
 * @param name 学院名字
 */
@Entity
@Table(name = "ACA_academy")
data class Academy(
    @Id
    @GeneratedValue
    val id: Long? = null,
    @Column(length = 60, nullable = false)
    val name: String = ""
) : Serializable {
    private constructor() : this(0L, "")

    override fun equals(other: Any?): Boolean {
        return this.hashCode() == other?.hashCode()
    }

    override fun hashCode(): Int {
        return id!!.hashCode()
    }
}