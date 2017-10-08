package me.maxct.academic.controller

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.Course
import me.maxct.academic.entity.Profile
import me.maxct.academic.entity.Semester
import me.maxct.academic.entity.User
import me.maxct.academic.repository.*
import me.maxct.academic.service.UserService
import me.maxct.academic.util.AppConst
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.security.Principal

/**
 * Created by imaxct on 17-9-2.
 */
@RestController
@RequestMapping("/u")
@PreAuthorize("hasRole('USER')")
class UserController {
    @Autowired
    private lateinit var semesterRepository: SemesterRepository
    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var majorRepository: MajorRepository
    @Autowired
    private lateinit var academyRepository: AcademyRepository
    @Autowired
    private lateinit var courseRepository: CourseRepository
    @Autowired
    private lateinit var settingRepository: SettingRepository

    //获取当前学期开设课程
    @GetMapping("/c/{id}")
    fun getCourses(@PathVariable id: Int): Msg<*> {
        val setting = settingRepository.findOne(AppConst.CONFIG_SELECT_ON)
        if (setting.value.equals("false", true))
            return Msg.err("当前选课未开始")
        val semester: Semester = semesterRepository.getCurrentSemester()
        return userService.getCourses(semester, id)
    }

    //获取所有已选课程
    @GetMapping("/ch")
    fun getChosenCourse(principal: Principal): Msg<*> =
        userService.getChosenCourse(User(username = principal.name))

    //获取奖惩记录
    @GetMapping("/r")
    fun getRecord(principal: Principal): Msg<*> =
        userService.getRecord(User(username = principal.name))

    //获取自己的信息
    @GetMapping("/i")
    fun getInfo(principal: Principal): Msg<*> =
        Msg.ok("ok", userService.getInfo(principal.name)?.profile ?: Profile())

    //选课
    @PostMapping("/c/{id}")
    fun chooseCourse(@PathVariable id: Long, principal: Principal): Msg<*> {
        val setting = settingRepository.findOne(AppConst.CONFIG_SELECT_ON)
        if (setting.value.equals("false", true))
            return Msg.err("当前选课未开始")
        val course = courseRepository.findOne(id)
        return userService.chooseCourse(User(username = principal.name), course)
    }

    //退选
    @PostMapping("/d/{id}")
    fun dropCourse(@PathVariable id: Long, principal: Principal): Msg<*> {
        val setting = settingRepository.findOne(AppConst.CONFIG_SELECT_ON)
        if (setting.value.equals("false", true))
            return Msg.err("当前选课未开始")
        return userService.dropCourse(User(username = principal.name), Course(id = id))
    }

    //获取本学期课表
    @GetMapping("/s")
    fun getCourseSchedule(principal: Principal): Msg<*> =
        userService.getCourseSchedule(
            User(username = principal.name),
            semesterRepository.getCurrentSemester()
        )

    //获取所有学院
    @GetMapping("/a")
    fun getAllAcademy(): Msg<*> = Msg.ok("ok", academyRepository.getAll())

    //获取所有专业
    @GetMapping("/m")
    fun getAllMajor(): Msg<*> = Msg.ok("ok", majorRepository.getAll())

    //获取公告
    @GetMapping("/n")
    fun getNotice(): Msg<*> = Msg.ok("ok", settingRepository.findOne(AppConst.CONFIG_NOTICE))

    //获取学期
    @GetMapping("/st")
    fun getSemesters(): Msg<*> = Msg.ok("ok", semesterRepository.findAll())
}