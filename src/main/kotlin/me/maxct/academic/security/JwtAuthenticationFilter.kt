package me.maxct.academic.security

import io.jsonwebtoken.JwtException
import me.maxct.academic.bean.Msg
import me.maxct.academic.exception.JwtAuthException
import me.maxct.academic.util.StringUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by imaxct on 17-9-2.
 */
@Component
class JwtAuthenticationFilter : OncePerRequestFilter() {

    @Autowired
    lateinit var jwtUtil: JwtUtil

    override fun doFilterInternal(request: HttpServletRequest?, response: HttpServletResponse?, filterChain: FilterChain?) {
        try {
            val authentication = jwtUtil.verify(request!!)
            SecurityContextHolder.getContext().authentication = authentication
            filterChain!!.doFilter(request, response)
        } catch (e: Exception) {
            when (e) {
                is JwtException,
                is JwtAuthException,
                is AuthenticationException ->
                    response!!.writer.write(StringUtil.toJson(Msg.Companion.err("认证失败, 请重新登录.")))
            }
        }
    }
}