package me.maxct.academic.controller

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.Role
import me.maxct.academic.entity.User
import me.maxct.academic.repository.RoleRepository
import me.maxct.academic.service.SystemService
import me.maxct.academic.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by imaxct on 17-9-3.
 */
@RestController
class AuthController {

    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var systemService: SystemService
    @Autowired
    private lateinit var roleRepository: RoleRepository

/*    @PostMapping("/auth/login")
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
    }*/

    @GetMapping("/test/tt")
    fun test(): Msg<*> {
        val user = userService.getInfo(2) ?: return Msg.err("kong")
        return Msg.ok("ok", user)
    }

}