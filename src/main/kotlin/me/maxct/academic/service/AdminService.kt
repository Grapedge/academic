package me.maxct.academic.service

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.Record
import me.maxct.academic.entity.Selection
import me.maxct.academic.entity.User

interface AdminService {

    /**
     * 添加,修改奖惩记录
     * @param operator 操作用户
     * @param record record
     * @return dto
     */
    fun saveRecord(operator: User, record: Record): Msg<*>

    /**
     * 删除奖惩记录
     * @param operator 操作用户
     * @param record record
     * @return dto
     */
    fun deleteRecord(operator: User, record: Record): Msg<*>

    /**
     * 登记成绩
     * @param operator 登记教工
     * @param selection 选课记录
     * @return dto
     */
    fun createScore(operator: User, selection: Selection): Msg<*>

    /**
     * 批量登记成绩
     * @param operator
     * @param list
     * @return dto
     */
    fun saveScoreInBatch(operator: User, list: List<Selection>): Msg<*>
}