package me.maxct.academic.service.impl

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.*
import me.maxct.academic.exception.ServiceException
import me.maxct.academic.repository.RecordRepository
import me.maxct.academic.repository.SelectionRepository
import me.maxct.academic.service.AdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by imaxct on 17-8-31.
 */
@Service
@Transactional
class AdminServiceImpl : AdminService {
    @Autowired
    private lateinit var recordRepository: RecordRepository
    @Autowired
    private lateinit var selectionRepository: SelectionRepository

    override fun saveRecord(operator: User, record: Record): Msg<*> =
        when {
            recordRepository.save(record) != null -> Msg.ok("操作成功")
            else -> Msg.err("保存错误")
        }

    override fun deleteRecord(operator: User, record: Record): Msg<*> =
        when {
            !operator.username.equals(record.performer?.username) -> Msg.err("无法删除, 仅能删除本人操作的记录.")
            (recordRepository.deleteById(record.id!!) == 1) -> Msg.ok("删除成功")
            else -> throw ServiceException("删除失败")
        }

    override fun createScore(operator: User, selection: Selection): Msg<*> =
        when {
            selection.id?.course?.teacher != operator -> Msg.err("非本人教授课程,没有权限操作")
            selectionRepository.updateSelectionScore(selection.id, selection.score) == 1 -> Msg.ok("操作成功")
            else -> Msg.err("操作失败,成绩录入后无法修改")
        }

    override fun saveScoreInBatch(operator: User, list: List<Selection>): Msg<*> {
        val cnt = list.sumBy { selectionRepository.updateSelectionScore(it.id!!, it.score) }
        return Msg.ok("更新了 $cnt 条记录, 有${list.size - cnt}条已经录入, 无法更改.")
    }
}