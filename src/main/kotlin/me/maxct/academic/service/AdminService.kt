package me.maxct.academic.service

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.Record
import me.maxct.academic.entity.Selection
import me.maxct.academic.entity.User

interface AdminService {

    /**
     * 添加奖惩记录
     * @param user 操作用户
     * @param record record
     * @return dto
     */
    fun createRecord(user: User, record: Record): Msg<*>

    /**
     * 修改奖惩记录
     * @param user 操作用户
     * @param record record
     * @return dto
     */
    fun updateRecord(user: User, record: Record): Msg<*>

    /**
     * 删除奖惩记录
     * @param user 操作用户
     * @param record record
     * @return dto
     */
    fun deleteRecord(user: User, record: Record): Msg<*>

    /**
     * 登记成绩
     * @param teacher 登记教工
     * @param selection 选课记录
     */
    fun createScore(teacher: User, selection: Selection): Msg<*>
}