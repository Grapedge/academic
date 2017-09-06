package me.maxct.academic.controller

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.*
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

    //录入成绩
    @PostMapping("/s/{cid}")
    fun updateScore(@PathVariable cid: Long, @RequestParam username: String, @RequestParam s: Double,
                    principal: Principal): Msg<*> =
        adminService.createScore(
            User(username = principal.name),
            Selection(
                id = SelectionId(Course(id = cid), User(username = username)),
                score = s
            )
        )
    
    //添加一条奖惩记录
    @PostMapping("/r")
    fun createRecord(@RequestParam user: String, @RequestParam reward: Boolean, @RequestParam detail: String,
                     principal: Principal): Msg<*> {
        val op = User(username = principal.name)
        val record = Record(
            user = User(username = user),
            performer = op,
            reward = reward,
            description = detail
        )
        return adminService.saveRecord(op, record)
    }

    //删除奖惩记录
    @DeleteMapping("/r/{id}")
    fun deleteRecord(@PathVariable id: Long, principal: Principal): Msg<*> =
        adminService.deleteRecord(User(username = principal.name), Record(id = id))

}