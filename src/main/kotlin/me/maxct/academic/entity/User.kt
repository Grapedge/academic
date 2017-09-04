package me.maxct.academic.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import javax.persistence.*

/**
 * 用户
 * @param username username
 * @param password password
 * @param roles roles
 * @param profile profile
 */
@Entity
@Table(name = "ACA_user")
data class User(

    @Id
    @Column(unique = true, length = 64)
    val username: String? = null,

    @JsonIgnore
    @Column(length = 64)
    val password: String? = null,

    @Column(length = 50)
    val roles: String = "ROLE_USER",

    @OneToOne
    @JoinColumn
    val profile: Profile? = null
) : Serializable {

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return username?.hashCode() ?: super.hashCode()
    }
}