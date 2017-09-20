package me.maxct.academic.service

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.*

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

    /**
     * 添加 修改学期
     * @param operator 操作者
     * @param semester 学期
     * @return dto
     */
    fun saveSemester(operator: User, semester: Semester): Msg<*>

    /**
     * 创建 修改专业
     * @param operator 操作者
     * @param major 专业
     * @return dto
     */
    fun saveMajor(operator: User, major: Major): Msg<*>

    /**
     * 获取用户信息
     * @param operator 操作者
     * @param id username
     * @return dto
     */
    fun getUserInfo(operator: User, id: String): Msg<*>

    fun updateProfileInBatch(operator: User, list: List<Profile>): Msg<*>
}