package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.*

/**
 * 用户
 * @param id id
 * @param username username
 * @param password password
 * @param roles roles
 * @param locked is locked
 * @param expired is expired
 * @param profile profile
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
    val roles: List<Role>? = null,
    val locked: Boolean = false,
    val expired: Boolean = false,
    @OneToOne
    @JoinColumn
    val profile: Profile? = null
) : Serializable {
    override fun equals(other: Any?): Boolean {
        return this.hashCode() == (other?.hashCode() ?: super.hashCode())
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: super.hashCode()
    }
}