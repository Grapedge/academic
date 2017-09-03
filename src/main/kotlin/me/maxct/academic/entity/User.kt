package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.*

/**
 * 用户
 * @param id id
 * @param username username
 * @param password password
 * @param roles roles
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
    @OneToMany(fetch = FetchType.EAGER)
    val roles: List<Role>? = null,
    @OneToOne
    @JoinColumn
    val profile: Profile? = null
) : Serializable {
    /*override fun getAuthorities(): List<SimpleGrantedAuthority> = roles!!.map { SimpleGrantedAuthority(it.authority) }

    override fun isEnabled(): Boolean = true

    override fun getUsername(): String = username!!

    override fun isCredentialsNonExpired(): Boolean = !expired

    override fun getPassword(): String = password!!

    override fun isAccountNonExpired(): Boolean = !expired

    override fun isAccountNonLocked(): Boolean = !locked*/

    override fun equals(other: Any?): Boolean {
        return this.hashCode() == (other?.hashCode() ?: super.hashCode())
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: super.hashCode()
    }
}