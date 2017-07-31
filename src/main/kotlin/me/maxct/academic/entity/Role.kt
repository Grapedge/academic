package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.*

/**
 * Created by imaxct on 17-7-24.
 * academic
 */
@Entity
@Table(name = "ACA_role")
data class Role(
        @Id
        @GeneratedValue
        val id: Long? = null,
        @Column(length = 10)
        val name: String? = null
) : Serializable {
    private constructor() : this(null, null)
}