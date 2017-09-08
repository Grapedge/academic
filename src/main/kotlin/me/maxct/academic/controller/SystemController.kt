package me.maxct.academic.controller

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.*
import me.maxct.academic.service.SystemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.security.Principal

/**
 * Created by imaxct on 17-9-6.
 */
@RestController
@RequestMapping("/s")
@PreAuthorize("hasRole('SYSTEM')")
class SystemController {
    @Autowired
    private lateinit var systemService: SystemService

    //创建学院
    @PostMapping("/a")
    fun createAcademy(@RequestParam name: String, principal: Principal): Msg<*> =
        systemService.saveAcademy(User(username = principal.name), Academy(name = name))

    //更新学院信息
    @PostMapping("/ua/{id}")
    fun updateAcademy(@PathVariable id: Long, @RequestParam name: String, principal: Principal): Msg<*> =
        systemService.saveAcademy(User(username = principal.name), Academy(id, name))

    //创建专业
    @PostMapping("/m")
    fun createMajor(@RequestParam name: String, @RequestParam aid: Long, principal: Principal): Msg<*> =
        systemService.saveMajor(User(username = principal.name), Major(name = name, academy = Academy(id = aid)))

    //更新专业信息
    @PostMapping("/um/{id}")
    fun updateMajor(@PathVariable id: Long, @RequestParam name: String, @RequestParam aid: Long,
                    principal: Principal): Msg<*> =
        systemService.saveMajor(
            User(username = principal.name),
            Major(id = id, name = name, academy = Academy(id = aid))
        )

    //获取用户信息
    @GetMapping("/i/{id}")
    fun getUserInfo(@PathVariable id: String, principal: Principal): Msg<*> =
        systemService.getUserInfo(User(username = principal.name), id)

    @PostMapping("/c")
    fun createCourse(@RequestParam name: String, @RequestParam aid: Long, @RequestParam sid: Long,
                     @RequestParam tid: String, @RequestParam credit: Double,
                     @RequestParam week: String, @RequestParam total: Int, @RequestParam day: Int,
                     @RequestParam courseOrder: Int,
                     principal: Principal): Msg<*> {

        val course = Course(
            courseName = name, academy = Academy(id = aid), semester = Semester(id = sid),
            teacher = User(username = tid), credit = credit, week = week, total = total, remaining = total,
            day = day, courseOrder = courseOrder, flag = Integer.parseInt(week.reversed(), 2)
        )

        //TODO
        //systemService.saveCourse()
        return Msg.err("")
    }
}