package me.maxct.academic.service.impl

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.*
import me.maxct.academic.exception.ServiceException
import me.maxct.academic.repository.*
import me.maxct.academic.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
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

    override fun register(username: String, password: String): Msg<*> =
        if (userRepository.exists(Example.of(User(username = username)))) {
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
            user.password == password -> Msg.ok("ok")
            else -> Msg.err("密码错误")
        }
    }

    override fun getInfo(id: Long): User? = userRepository.findOne(id)

    override fun chooseCourse(user: User, course: Course): Msg<*> {
        val sid = SelectionId(course, user)
        return if (selectionRepository.exists(sid))
            Msg.err("已经选过${course.id?.name}了")
        else if (selectionRepository.save(Selection(id = sid, score = -1.0)) != null
            && courseRepository.decreaseCourseRemaining(course.id!!) == 1L)
            Msg.ok("选课成功")
        else throw ServiceException("选课失败,稍候再试")
    }

    override fun dropCourse(user: User, course: Course): Msg<*> {
        val sid = SelectionId(course, user)
        return if (!selectionRepository.exists(sid))
            Msg.err("未选择此课程")
        else if (courseRepository.increaseCourseRemaining(course.id!!) == 1L) {
            selectionRepository.delete(sid)
            Msg.ok("退选成功")
        } else throw ServiceException("退选失败,稍候再试")
    }

    override fun getChosenCourse(user: User): Msg<*>
        = Msg.ok("ok", selectionRepository.getSelectionByUser(user))

    override fun getCourseSchedule(user: User, semester: Semester): Msg<*>
        = Msg.ok("ok", selectionRepository.getSelectionBySemesterAndUser(semester, user))

    override fun getRecord(user: User): Msg<*> = Msg.ok("ok", recordRepository.getRecordByUser(user))

    override fun getCourses(semester: Semester): Msg<*> =
        Msg.ok("ok", courseRepository.getCourseBySemester(semester))
}