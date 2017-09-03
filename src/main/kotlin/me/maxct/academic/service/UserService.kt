package me.maxct.academic.service

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.Course
import me.maxct.academic.entity.Semester
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
     * 获取个人信息
     * @param username 用户名
     * @return user
     */
    fun getInfo(username: String): User?

    /**
     * 用户选课
     * @param user 操作用户
     * @param course 课程
     * @return dto
     */
    fun chooseCourse(user: User, course: Course): Msg<*>

    /**
     * 退选课程
     * @param user user
     * @param course course
     * @return dto
     */
    fun dropCourse(user: User, course: Course): Msg<*>

    /**
     * 获取所有已选课程
     * @param user user
     * @return dto
     */
    fun getChosenCourse(user: User): Msg<*>

    /**
     * 获取课表
     * @param user user
     * @return dto
     */
    fun getCourseSchedule(user: User, semester: Semester): Msg<*>

    /**
     * 获取奖惩记录
     * @param user user
     * @return dto
     */
    fun getRecord(user: User): Msg<*>

    /**
     * 获取本学期开设课程
     */
    fun getCourses(semester: Semester): Msg<*>
}