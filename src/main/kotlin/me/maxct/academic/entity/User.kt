package me.maxct.academic.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
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
        private val username: String? = null,
        @Column(length = 64)
        private val password: String? = null,
        @ManyToMany
        val roles: List<Role>? = null,
        val locked: Boolean = false,
        val expired: Boolean = false,
        @OneToOne
        @JoinColumn
        val profile: Profile? = null
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> = roles!!.map { SimpleGrantedAuthority(it.name) }

    override fun isEnabled(): Boolean = true

    override fun getUsername(): String = username!!

    override fun isCredentialsNonExpired(): Boolean = false

    override fun getPassword(): String = password!!

    override fun isAccountNonExpired(): Boolean = expired

    override fun isAccountNonLocked(): Boolean = locked

    private constructor() : this(locked = false, expired = false)
}