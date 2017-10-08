package me.maxct.academic.controller

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import java.security.Principal

/**
 * Created by imaxct on 17-9-3.
 */
@Controller
class IndexController {

    @GetMapping("/login")
    fun login(): String = "login"

    @GetMapping("/home")
    fun home(principal: Principal): String {
        return if (principal is UsernamePasswordAuthenticationToken) {
            val str = principal.authorities.joinToString(",")
            when {
                str.contains("SYSTEM") -> "redirect:/system.html"
                str.contains("ADMIN") -> "redirect:/admin.html"
                else -> "redirect:/"
            }
        } else "redirect:/"
    }
}