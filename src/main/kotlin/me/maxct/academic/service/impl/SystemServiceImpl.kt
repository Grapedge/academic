package me.maxct.academic.service.impl

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.Academy
import me.maxct.academic.entity.Course
import me.maxct.academic.entity.User
import me.maxct.academic.service.SystemService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by imaxct on 17-8-31.
 */
@Service
@Transactional
class SystemServiceImpl : SystemService {

    override fun importStudentInfo(users: List<User>): Msg<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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