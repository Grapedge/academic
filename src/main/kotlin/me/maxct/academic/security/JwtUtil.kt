package me.maxct.academic.security

import io.jsonwebtoken.*
import me.maxct.academic.exception.JwtAuthException
import me.maxct.academic.security.bean.JwtTokenAuthentication
import me.maxct.academic.security.bean.JwtUser
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.web.util.WebUtils
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest

/**
 * Created by imaxct on 17-9-2.
 */
@Component
class JwtUtil {
    @Value("\${jwt.key}")
    lateinit var signKey: String
    @Value("\${jwt.token-name}")
    lateinit var tokenName: String

    fun create(jwtUser: JwtUser): String =
        Jwts.builder()
            .setExpiration(Date(System.currentTimeMillis() + 1000L * 60L * 90L))
            .setSubject(jwtUser.username)
            .claim("id", jwtUser.id)
            .claim("scopes", jwtUser.roles)
            .signWith(SignatureAlgorithm.HS512, signKey)
            .compact()

    fun parse(token: String): Jws<Claims> {
        try {
            return Jwts.parser().setSigningKey(signKey).parseClaimsJws(token)
        } catch (e: Exception) {
            when (e) {
                is UnsupportedJwtException,
                is MalformedJwtException,
                is SignatureException,
                is ExpiredJwtException ->
                    throw JwtAuthException("认证错误,请重新登录")
                else -> throw e
            }
        }
    }

    fun parseJwtUser(token: String): JwtUser {
        val claims: Claims = parse(token).body
        return JwtUser(
            id = claims["id"] as Long,
            username = claims.subject,
            roles = claims["scopes"] as List<String>
        )
    }

    fun verify(req: HttpServletRequest): Authentication {
        val cookie: Cookie = WebUtils.getCookie(req, tokenName) ?: throw JwtAuthException("认证过期,请重新登录.")
        val rawToken : String = cookie.value ?: throw JwtAuthException("认证过期,请重新登录!")
        val jwtUser = parseJwtUser(rawToken)
        return JwtTokenAuthentication(jwtUser = jwtUser, token = rawToken, authenticated = true)
    }
}