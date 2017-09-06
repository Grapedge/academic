package me.maxct.academic.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * Created by imaxct on 17-9-3.
 */
@Controller
class IndexController {

    @GetMapping("/login")
    fun login(): String = "login"
}