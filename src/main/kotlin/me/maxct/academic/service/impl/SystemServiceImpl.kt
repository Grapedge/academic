package me.maxct.academic.service.impl

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.*
import me.maxct.academic.exception.ServiceException
import me.maxct.academic.repository.*
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
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var academyRepository: AcademyRepository

    @Autowired
    private lateinit var courseRepository: CourseRepository

    @Autowired
    private lateinit var semesterRepository: SemesterRepository

    @Autowired
    private lateinit var majorRepository: MajorRepository

    @Autowired
    private lateinit var profileRepository: ProfileRepository

    override fun importStudentInfo(operator: User, users: List<User>): Msg<*> {
        userRepository.save(users)
        return Msg.ok("执行完毕")
    }

    override fun updateUserInfo(operator: User, user: User): Msg<*> =
        if (userRepository.save(user) != null) Msg.ok("更新成功")
        else Msg.err("更新失败")

    override fun saveAcademy(operator: User, academy: Academy): Msg<*> =
        if (academyRepository.save(academy) == null) Msg.err("操作失败")
        else Msg.ok("操作成功")

    override fun saveCourse(operator: User, course: Course): Msg<*> =
        if (courseRepository.save(course) == null) Msg.err("操作失败")
        else Msg.ok("操作成功")

    override fun deleteCourse(operator: User, course: Course): Msg<*> =
        when (courseRepository.deleteById(course.id!!)) {
            1L -> Msg.ok("删除成功")
            else -> throw ServiceException("删除失败")
        }

    override fun saveSemester(operator: User, semester: Semester): Msg<*> = when {
        semesterRepository.existSemester(semester.beginDate!!, semester.endDate!!) ->
            Msg.err("操作失败,与之前学期存在重合")
        semesterRepository.save(semester) != null ->
            Msg.ok("操作成功")
        else ->
            Msg.err("操作失败,稍候再试")
    }

    override fun saveMajor(operator: User, major: Major): Msg<*> =
        if (majorRepository.save(major) != null) Msg.ok("保存成功")
        else Msg.err("保存失败")

    override fun getUserInfo(operator: User, id: String): Msg<*> {
        val u = userRepository.findOneByUsername(id) ?: return Msg.err("用户不存在")
        return Msg.ok("ok", u)
    }

    override fun updateProfileInBatch(operator: User, list: List<Profile>): Msg<*> =
        Msg.ok("", profileRepository.save(list).size)

    override fun importProfileInBatch(operator: User, list: List<Profile>, isTeacher: Boolean): Msg<*> {
        val res = profileRepository.save(list)
        var role = "ROLE_USER"
        if (isTeacher) role = "ROLE_USER,ROLE_ADMIN"
        println(role)
        val arr = ArrayList<User>()
        res.mapTo(arr) {
            User(
                username = it.workNo,
                password = it.idNo?.substring(it.idNo.length - 6, it.idNo.length) ?: "123456",
                roles = role,
                profile = it
            )
        }
        val num = userRepository.save(arr).size
        return Msg.ok("保存了$num 条数据")
    }
}