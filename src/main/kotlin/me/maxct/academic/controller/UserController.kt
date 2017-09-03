package me.maxct.academic.controller

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.Semester
import me.maxct.academic.entity.User
import me.maxct.academic.repository.SemesterRepository
import me.maxct.academic.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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

    @GetMapping("/c")
    fun getCourse(): Msg<*> {
        val semester: Semester = semesterRepository.getCurrentSemester()
        return userService.getCourses(semester)
    }

    @GetMapping("/ch")
    fun getChosenCourse(principal: Principal): Msg<*> =
        userService.getChosenCourse(User(username = principal.name))

    @GetMapping("/r")
    fun getRecord(principal: Principal): Msg<*> =
        userService.getRecord(User(username = principal.name))

}