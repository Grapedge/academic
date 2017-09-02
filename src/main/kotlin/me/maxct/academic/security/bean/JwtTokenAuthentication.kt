package me.maxct.academic.security.bean

import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority

/**
 * Created by imaxct on 17-9-2.
 */
data class JwtTokenAuthentication(
    val jwtUser: JwtUser,
    val authenticated: Boolean,
    val token: String
) : Authentication {

    override fun getAuthorities(): List<SimpleGrantedAuthority> =
        jwtUser.roles.map { i -> SimpleGrantedAuthority(i) }

    override fun setAuthenticated(isAuthenticated: Boolean) {
    }

    override fun getName(): String = jwtUser.username

    override fun getCredentials(): Any = token

    override fun getPrincipal(): Any = jwtUser.id

    override fun isAuthenticated(): Boolean = authenticated

    override fun getDetails(): Any = jwtUser
}