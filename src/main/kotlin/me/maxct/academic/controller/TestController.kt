package me.maxct.academic.controller

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.User
import me.maxct.academic.service.AdminService
import me.maxct.academic.util.StringUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by imaxct on 17-9-1.
 */
@RestController
@RequestMapping("/test")
class TestController {

    @GetMapping("/t1")
    fun testUtil(): String  = StringUtil.toJson(Msg.ok("ok"))

}