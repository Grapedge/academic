package me.maxct.academic.security

import me.maxct.academic.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

/**
 * Created by imaxct on 17-9-3.
 */
@Component
class CustomUserDetailsService : UserDetailsService {
    @Autowired
    private lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findOneByUsername(username!!) ?: throw UsernameNotFoundException("用户不存在")
        return User(user.username, user.password, user.roles!!.map { SimpleGrantedAuthority(it.authority) })
    }
}