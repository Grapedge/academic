package me.maxct.academic.controller

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.Course
import me.maxct.academic.entity.User
import me.maxct.academic.repository.CourseRepository
import me.maxct.academic.repository.SelectionRepository
import me.maxct.academic.service.AdminService
import me.maxct.academic.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.security.Principal

/**
 * Created by imaxct on 17-9-5.
 */
@RestController
@RequestMapping("/a")
@PreAuthorize("hasRole('ADMIN')")
class AdminController {
    @Autowired
    private lateinit var adminService: AdminService
    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var courseRepository: CourseRepository
    @Autowired
    private lateinit var selectionRepository: SelectionRepository

    //获取自己负责的课
    @GetMapping("/c")
    fun getMyCourse(principal: Principal): Msg<*> =
        Msg.ok("ok", courseRepository.getCourseByTeacher(
            User(username = principal.name))
        )
    //查看课的选课情况
    @GetMapping("/s/{id}")
    fun getSelection(@PathVariable id: Long, principal: Principal): Msg<*> =
        Msg.ok("ok", selectionRepository.getSelectionByCourse(Course(id = id)))

    @PostMapping("/r")
    fun createRecord(): Msg<*> = Msg.ok("")
}