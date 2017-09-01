package me.maxct.academic.service.impl

import me.maxct.academic.bean.Msg
import me.maxct.academic.entity.Record
import me.maxct.academic.entity.Selection
import me.maxct.academic.entity.User
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
    val recordRepository: RecordRepository? = null
    @Autowired
    val selectionRepository: SelectionRepository? = null

    override fun saveRecord(operator: User, record: Record): Msg<*>
        = if (recordRepository!!.save(record) != null) Msg.ok("ok")
    else Msg.err("保存错误")

    override fun deleteRecord(operator: User, record: Record): Msg<*> =
        if (recordRepository!!.deleteById(record.id!!).toInt() == 1)
            Msg.ok("删除成功")
        else throw ServiceException("删除失败")

    override fun createScore(operator: User, selection: Selection): Msg<*> =
        if (selectionRepository!!.updateSelectionScore(selection.id!!, selection.score) == 1)
            Msg.ok("操作成功")
        else Msg.err("操作失败,成绩录入后无法修改")
}