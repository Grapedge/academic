package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.*

/**
 * Created by imaxct on 17-8-1.
 * academic
 */
@Entity
@Table(name = "ACA_major")
data class Major(
        @Id
        val id: Long,
        @Column(length = 60)
        val name: String? = null,
        @ManyToOne
        @JoinColumn
        val academy: Academy? = null
) : Serializable