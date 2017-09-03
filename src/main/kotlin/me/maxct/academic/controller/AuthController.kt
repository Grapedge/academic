package me.maxct.academic.controller

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.User
import me.maxct.academic.security.JwtUtil
import me.maxct.academic.security.bean.JwtUserFactory
import me.maxct.academic.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

/**
 * Created by imaxct on 17-9-3.
 */
@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var jwtUtil: JwtUtil

    @PostMapping("/login")
    fun login(@RequestParam username: String, @RequestParam password: String, resp: HttpServletResponse): Msg<*> {
        val msg = userService.login(username, password)
        return if (msg.ok) {
            val token = jwtUtil.create(JwtUserFactory.build(msg.obj as User))
            val cookie = Cookie(jwtUtil.tokenName, token)
            cookie.path = "/"
            cookie.isHttpOnly = true
            cookie.maxAge = 60 * 90
            resp.addCookie(cookie)
            Msg.ok("ok")
        } else msg
    }


}