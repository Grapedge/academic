package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.*

/**
 * 专业
 * Created by imaxct on 17-8-1.
 * academic
 */
@Entity
@Table(name = "ACA_major")
data class Major(
    @Id
    val id: Long? = null,
    @Column(length = 60)
    val name: String? = null,
    @ManyToOne
    @JoinColumn
    val academy: Academy? = null
) : Serializable {
    private constructor() : this(null)
}