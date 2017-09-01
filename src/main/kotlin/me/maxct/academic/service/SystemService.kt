package me.maxct.academic.service

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.Academy
import me.maxct.academic.entity.Course
import me.maxct.academic.entity.User

interface SystemService {

    /**
     * 批量导入学生信息(耗时长)
     * @param users 用户
     * @return dto
     */
    fun importStudentInfo(operator: User, users: List<User>): Msg<*>

    /**
     * 更新用户信息
     * @param operator 操作用户
     * @param user 要更新的用户
     * @return dto
     */
    fun updateUserInfo(operator: User, user: User): Msg<*>

    /**
     * 添加学院, 修改学院
     * @param operator 操作用户
     * @param academy academy
     * @return dto
     */
    fun saveAcademy(operator: User, academy: Academy): Msg<*>

    /**
     * 添加课程, 修改课程
     * @param operator 操作用户
     * @param course 课程
     * @return dto
     */
    fun saveCourse(operator: User, course: Course): Msg<*>

    /**
     * 删除课程
     * @param operator 操作用户
     * @param course 课程
     * @return dto
     */
    fun deleteCourse(operator: User, course: Course): Msg<*>
}