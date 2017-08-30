package me.maxct.academic.service.impl

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.CourseId
import me.maxct.academic.entity.User
import me.maxct.academic.repository.*
import me.maxct.academic.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example


/**
 * Created by imaxct on 17-8-30.
 */
class UserServiceImpl : UserService {
    @Autowired val userRepository: UserRepository? = null
    @Autowired val courseRepository: CourseRepository? = null
    @Autowired val recordRepository: RecordRepository? = null
    @Autowired val profileRepository: ProfileRepository? = null
    @Autowired val selectionRepository: SelectionRepository? = null

    override fun register(username: String, password: String): Msg<*> {
        return if (userRepository!!.exists(Example.of(User(username = username)))) {
            Msg.err("用户名已存在")
        } else {
            if (userRepository.save(User(username = username, password = password)) != null)
                Msg.ok("注册成功")
            else
                Msg.err("注册失败")
        }
    }

    override fun login(username: String, password: String): Msg<*> {
        val user = userRepository!!.findOne(Example.of(User(username = username)))
        return when {
            user == null -> Msg.err("用户错误")
            user.password == password -> Msg.ok("ok")
            else -> Msg.err("密码错误")
        }

    }

    override fun getInfo(id: Long): User? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun chooseCourse(user: User, courseId: CourseId): Msg<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dropCourse(user: User, courseId: CourseId): Msg<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getChosenCourse(user: User): Msg<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCourseSchedule(user: User): Msg<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRecord(user: User): Msg<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}