package me.maxct.academic.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import javax.persistence.*

/**
 * 专业
 * @param id 专业id
 * @param name 专业名称
 * @param academy 学院
 */
@Entity
@Table(name = "ACA_major")
data class Major(
    @Id
    @GeneratedValue
    val id: Long? = null,

    @Column(length = 60)
    val name: String? = null,

    @ManyToOne
    @JoinColumn(name = "academy_id")
    val academy: Academy? = null
) : Serializable {
    private constructor() : this(null)
}