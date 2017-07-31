package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.*

/**
 * Created by imaxct on 17-7-24.
 * academic
 */
@Entity
@Table(name = "ACA_user")
data class User(
        @Id
        @GeneratedValue
        val id: Long? = null,
        @Column(unique = true, length = 64)
        val username: String? = null,
        @Column(length = 64)
        val password: String? = null,
        @ManyToMany
        val roles: List<Role>? = null
) : Serializable {
    private constructor() : this(0L, null, null, null)
}