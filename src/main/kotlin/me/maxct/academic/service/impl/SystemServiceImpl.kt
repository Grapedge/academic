package me.maxct.academic.service.impl

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.Academy
import me.maxct.academic.entity.Course
import me.maxct.academic.entity.User
import me.maxct.academic.exception.ServiceException
import me.maxct.academic.repository.AcademyRepository
import me.maxct.academic.repository.CourseRepository
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

    @Autowired
    private val academyRepository: AcademyRepository? = null

    @Autowired
    private val courseRepository: CourseRepository? = null

    override fun importStudentInfo(operator: User, users: List<User>): Msg<*> {
        userRepository!!.save(users)
        return Msg.ok("执行完毕")
    }

    override fun updateUserInfo(operator: User, user: User): Msg<*> =
        if (userRepository!!.save(user) != null) Msg.ok("更新成功")
        else Msg.err("更新失败")

    override fun saveAcademy(operator: User, academy: Academy): Msg<*> =
        if (academyRepository!!.save(academy) == null) Msg.err("操作失败")
        else Msg.ok("操作成功")

    override fun saveCourse(operator: User, course: Course): Msg<*> =
        if (courseRepository!!.save(course) == null) Msg.err("操作失败")
        else Msg.ok("操作成功")

    override fun deleteCourse(operator: User, course: Course): Msg<*> =
        when (courseRepository!!.deleteById(course.id!!)) {
            1L -> Msg.ok("删除成功")
            else -> throw ServiceException("删除失败")
        }
}