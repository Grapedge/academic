package me.maxct.academic.controller

import me.maxct.academic.bean.Msg
import me.maxct.academic.bean.NetMsg
import me.maxct.academic.bean.Scores
import me.maxct.academic.entity.*
import me.maxct.academic.repository.CourseRepository
import me.maxct.academic.repository.RecordRepository
import me.maxct.academic.repository.SelectionRepository
import me.maxct.academic.service.AdminService
import me.maxct.academic.service.UserService
import me.maxct.academic.util.StringUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
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
    @Autowired
    private lateinit var recordRepository: RecordRepository

    //获取自己负责的课
    @GetMapping("/c")
    fun getMyCourse(principal: Principal): Msg<*> {
        val list = courseRepository.getCourseByTeacher(
            User(username = principal.name)
        )
        val arr = ArrayList<Any>()
        for (x in list) {
            val o = NetMsg()
            o.put("id", x!!.id)
                .put("name", x.courseName)
                .put("week", StringUtil.readWeek(x.week!!))
            o.put("time", "周" + StringUtil.getWeekDayName(x.day!!) + "第${x.courseOrder}节")
                .put("academy", x.academy!!.name)
                .put("semester", x.semester?.name)
                .put("number", x.total!! - x.remaining!!)
                .put("total", x.total)
                .put("location", x.location)
                .put("credit", x.credit)
            arr.add(o.list)
        }
        return Msg.ok("ok", arr)
    }

    //查看课的选课情况
    @GetMapping("/s/{id}")
    fun getSelection(@PathVariable id: Long, principal: Principal): Msg<*> {
        val list = selectionRepository.getSelectionByCourse(Course(id = id))
        val arr = ArrayList<Any>()
        for ((idx, score) in list) {
            val o = NetMsg()
            o.put("stuNo", idx?.user?.username)
                .put("name", idx?.user?.profile?.name)
                .put("gender", idx?.user?.profile?.gender)
                .put("academy", idx?.user?.profile?.major?.academy?.name)
                .put("major", idx?.user?.profile?.major?.name)
                .put("unit", idx?.user?.profile?.unit)
                .put("score", score)
            arr.add(o.list)
        }
        return Msg.ok("ok", arr)
    }

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
    fun createRecord(@RequestParam stuNo: String, @RequestParam reward: Boolean, @RequestParam detail: String,
                     principal: Principal): Msg<*> {
        val op = User(username = principal.name)
        val record = Record(
            user = User(username = stuNo),
            performer = op,
            reward = reward,
            description = detail
        )
        return adminService.saveRecord(op, record)
    }

    //删除奖惩记录
    @DeleteMapping("/r/{id}")
    fun deleteRecord(@PathVariable id: Long, principal: Principal): Msg<*> =
        adminService.deleteRecord(User(username = principal.name), recordRepository.findOne(id))

    //查询学生信息
    @PostMapping("/i")
    fun getInfo(@RequestParam stuNo: String): Msg<*> {
        val user = userService.getInfo(stuNo) ?: return Msg.err("用户不存在")
        val msg = NetMsg()
        msg.put("name", user.profile?.name)
            .put("stuNo", user.username)
            .put("academy", user.profile?.major?.academy?.name)
            .put("major", user.profile?.major?.name)
            .put("unit", user.profile?.unit)
        return Msg.ok("ok", msg.list)
    }

    //获取自己操作的奖惩记录
    @GetMapping("/r")
    fun getMyRecord(principal: Principal): Msg<*> {
        val list = recordRepository.getRecordByPerformer(
            User(username = principal.name),
            Sort(Sort.Direction.DESC, "id")
        )
        val arr = ArrayList<Any>()
        for (x in list) {
            val o = NetMsg()
            o.put("id", x.id)
                .put("stuNo", x.user?.username)
                .put("name", x.user?.profile?.name)
                .put("reward", x.reward)
                .put("detail", x.description)
                .put("date", x.timestamp)
            arr.add(o.list)
        }
        return Msg.ok("ok", arr)
    }

    //批量更新成绩
    @PostMapping("/up")
    fun updateScoreBatch(@RequestBody scores: Scores, principal: Principal): Msg<*> {
        val cid = Course(id = scores.cid)
        val list = ArrayList<Selection>()
        scores.list
            .filter { it.credit >= 0 }
            .mapTo(list) {
                Selection(
                    id = SelectionId(
                        course = cid,
                        user = User(username = it.stuNo)
                    ),
                    score = it.credit
                )
            }
        return adminService.saveScoreInBatch(
            User(username = principal.name),
            list
        )
    }
}