package me.maxct.academic.service.impl

import me.maxct.academic.bean.Msg
import me.maxct.academic.bean.NetMsg
import me.maxct.academic.entity.*
import me.maxct.academic.exception.ServiceException
import me.maxct.academic.repository.*
import me.maxct.academic.service.UserService
import me.maxct.academic.util.StringUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


/**
 * Created by imaxct on 17-8-30.
 */
@Service
@Transactional
class UserServiceImpl : UserService {
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var courseRepository: CourseRepository
    @Autowired
    private lateinit var recordRepository: RecordRepository
    @Autowired
    private lateinit var selectionRepository: SelectionRepository
    @Autowired
    private lateinit var semesterRepository: SemesterRepository

    override fun register(username: String, password: String): Msg<*> =
        if (userRepository.exists(username)) {
            Msg.err("用户名已存在")
        } else {
            if (userRepository.save(User(username = username, password = password)) != null)
                Msg.ok("注册成功")
            else
                Msg.err("注册失败")
        }

    override fun login(username: String, password: String): Msg<*> {
        val user = userRepository.findOne(Example.of(User(username = username)))
        return when {
            user == null -> Msg.err("用户错误")
            user.password == password -> Msg.ok("ok", user)
            else -> Msg.err("密码错误")
        }
    }

    override fun getInfo(username: String): User? = userRepository.findOne(username)

    override fun chooseCourse(user: User, course: Course): Msg<*> {
        val sid = SelectionId(course, user)
        if (selectionRepository.exists(sid))
            return Msg.err("已经选过这门课了")
        else {
            val list = selectionRepository.getSelectionBySemesterAndUser(
                semesterRepository.getCurrentSemester(),
                user
            )
            var flag = true
            var name = ""
            for ((id) in list) {
                if (id?.course?.day == course.day
                    && id?.course?.courseOrder == course.courseOrder
                    && id?.course?.flag?.and(course.flag!!) != 0
                    ) {
                    flag = false
                    name = id?.course?.courseName!!
                    break
                }
            }
            return if (!flag)
                Msg.err("选课失败, 与 $name 上课时间冲突")
            else if (selectionRepository.saveAndFlush(Selection(sid)) != null
                && courseRepository.decreaseCourseRemaining(course.id!!) == 1)
                Msg.ok("选课成功")
            else
                throw ServiceException("选课失败,稍候再试")
        }
    }

    override fun dropCourse(user: User, course: Course): Msg<*> {
        val sid = SelectionId(course, user)
        return if (!selectionRepository.exists(sid))
            Msg.err("未选择此课程")
        else {
            val c = courseRepository.findOne(course.id) ?: throw ServiceException("课程不存在")
            if (c.semester!!.id != semesterRepository.getCurrentSemester().id) {
                return Msg.err("不能退选此课程")
            } else if (courseRepository.increaseCourseRemaining(course.id!!) == 1
                && selectionRepository.deleteById(sid) == 1) {
                Msg.ok("退选成功")
            } else throw ServiceException("退选失败,稍候再试")
        }
    }

    override fun getChosenCourse(user: User): Msg<*> {
        val list = selectionRepository.getSelectionByUser(user)
        val arr = ArrayList<Any>()
        for ((id, score) in list) {
            val obj = NetMsg()
            obj.put("courseId", id?.course?.id)
                .put("semester", id?.course?.semester?.name)
                .put("courseName", id?.course?.courseName)
                .put("teacher", id?.course?.teacher?.profile?.name)
                .put("credit", id?.course?.credit)
                .put("total", id?.course?.total)
                .put("score", score)
            arr.add(obj.list)
        }
        return Msg.ok("ok", arr)
    }

    override fun getCourseSchedule(user: User, semester: Semester): Msg<*> {
        val list = selectionRepository.getSelectionBySemesterAndUser(semester, user)
        val arr = ArrayList<Any>()
        for ((id) in list) {
            val res = NetMsg()
            res.put("id", id!!.course!!.id)
                .put("name", id.course?.courseName)
                .put("teacher", id.course?.teacher?.profile?.name)
                .put("location", id.course?.location)
                .put("credit", id.course?.credit)
                .put("day", id.course?.day)
                .put("week", StringUtil.readWeek(id.course?.week!!))
                .put("order", id.course.courseOrder)
            val time = "周" + StringUtil.getWeekDayName(id.course.day!!) + "第${id.course.courseOrder}节"
            res.put("time", time).put("number", "${id.course.remaining}/${id.course.total}")
            arr.add(res.list)
        }
        return Msg.ok("ok", arr)
    }

    override fun getRecord(user: User): Msg<*> {
        val list = recordRepository.getRecordByUser(user, Sort(Sort.Direction.DESC, "id"))
        val arr = ArrayList<Any>()
        for (x in list) {
            val e = NetMsg()
            e.put("timestamp", x!!.timestamp).put("reward", x.reward)
                .put("detail", x.description)
            arr.add(e.list)
        }
        return Msg.ok("ok", arr)
    }

    override fun getCourses(semester: Semester, page: Int): Msg<*> {
        /*val list = courseRepository.getCourseBySemester(semester)*/
        val pageable = PageRequest(page, 20, Sort(Sort.Direction.DESC, "id"))
        val list = courseRepository.getCourseBySemester(semester, pageable)
        val pageDTO = list.map(this::convertToDTO)
        return Msg.ok("ok", pageDTO)
    }

    fun convertToDTO(x: Course?): MutableMap<String, Any?> {
        val e = NetMsg()
        e.put("id", x!!.id)
            .put("name", x.courseName)
            .put("semester", x.semester!!.name)
            .put("week", StringUtil.readWeek(x.week!!))
            .put("day", x.day)
            .put("order", x.courseOrder)
            .put("teacher", x.teacher?.profile?.name)
            .put("credit", x.credit)
            .put("choose", "${x.remaining}/${x.total}")
            .put("academy", x.academy!!.name)
        return e.list
    }


}