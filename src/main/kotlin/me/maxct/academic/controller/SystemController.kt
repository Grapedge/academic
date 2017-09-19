package me.maxct.academic.controller

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.*
import me.maxct.academic.repository.MajorRepository
import me.maxct.academic.repository.ProfileRepository
import me.maxct.academic.repository.SettingRepository
import me.maxct.academic.service.SystemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
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
    @Autowired
    private lateinit var settingRepository: SettingRepository
    @Autowired
    private lateinit var profileRepository: ProfileRepository
    @Autowired
    private lateinit var majorRepository: MajorRepository

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

    //创建课程
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
        return systemService.saveCourse(User(username = principal.name), course)
    }

    //获取所有的设置
    @GetMapping("/s")
    fun getAllSettings(): Msg<*> {
        val list = settingRepository.findAll()
        val map = HashMap<String, Any>()
        for ((name, value) in list) {
            map.put(name!!, value!!)
        }
        return Msg.ok("ok", map)
    }

    //更新设置
    @PostMapping("/s")
    fun updateSetting(@RequestParam name: String, @RequestParam value: String): Msg<*> =
        if (settingRepository.save(Setting(name, value)) != null) Msg.ok("ok")
        else Msg.err("更新失败")

    @PostMapping("/info")
    fun getUsersInfo(@RequestParam(required = false) workNo: String?, @RequestParam(required = false) gender: String?,
                     @RequestParam(required = false) major: Long?, @RequestParam(required = false) role: String?): Msg<*> {
        val m = if (major != null) majorRepository.findOne(major) else null
        val no = if (workNo == null || "" == workNo) null else workNo
        val g = if (gender == null || gender == "") null else gender

        val e = Example.of(Profile(
            workNo = no,
            gender = g,
            major = m,
            user = User(
                roles = role
            )
        ), ExampleMatcher.matching().withIgnoreNullValues())
        val res = profileRepository.findAll(e)
        if (m != null) res.removeIf({ it -> m != it.major })
        return Msg.ok("ok", res)
    }
}