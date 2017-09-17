package me.maxct.academic.repository

import me.maxct.academic.entity.Record
import me.maxct.academic.entity.User
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by imaxct on 17-8-30.
 */
@Repository
interface RecordRepository : JpaRepository<Record, Long> {
    fun getRecordByUser(user: User, sort: Sort): List<Record?>
    fun deleteById(id: Long): Int
    fun getRecordByPerformer(performer: User, sort: Sort) :List<Record>
}