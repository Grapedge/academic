package me.maxct.academic.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by imaxct on 17-9-3.
 */
@Controller
class IndexController {

    @GetMapping("/login")
    fun login(): String = "login"

    @ResponseBody
    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    fun index(): String = "AAA"

    @ResponseBody
    @GetMapping("/b")
    @PreAuthorize("hasRole('ADMIN')")
    fun index2(): String = "BBB"
}