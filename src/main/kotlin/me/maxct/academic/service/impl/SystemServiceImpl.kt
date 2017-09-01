package me.maxct.academic.service.impl

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.Academy
import me.maxct.academic.entity.Course
import me.maxct.academic.entity.User
import me.maxct.academic.repository.UserRepository
import me.maxct.academic.service.SystemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by imaxct on 17-8-31.
 */
@Service
@Transactional
class SystemServiceImpl : SystemService {

    @Autowired
    private val userRepository: UserRepository? = null

    override fun importStudentInfo(operator: User, users: List<User>): Msg<*> {
        userRepository!!.save(users)
        return Msg.ok("执行完毕")
    }

    override fun updateUserInfo(operator: User, user: User): Msg<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveAcademy(operator: User, academy: Academy): Msg<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveCourse(operator: User, course: Course): Msg<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteCourse(operator: User, course: Course): Msg<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}