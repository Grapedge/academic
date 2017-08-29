package me.maxct.academic.service

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.CourseId
import me.maxct.academic.entity.User

interface UserService {

    /**
     * 注册用户(通常不对外开放)
     * @param username 用户名
     * @param password 密码
     * @return dto
     */
    fun register(username: String, password: String): Msg<*>

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return dto
     */
    fun login(username: String, password: String): Msg<*>

    /**
     * 获取个人信息(本人)
     * @param id 用户id
     * @return dto
     */
    fun info(id: Long): Msg<*>

    /**
     * 用户选课
     * @param user 操作用户
     * @param courseId 课程id
     * @return dto
     */
    fun chooseCourse(user: User, courseId: CourseId): Msg<*>

    /**
     * 退选课程
     */
    fun dropCourse(user: User, courseId: CourseId): Msg<*>
}