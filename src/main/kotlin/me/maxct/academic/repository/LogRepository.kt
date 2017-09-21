package me.maxct.academic.repository

import me.maxct.academic.entity.Log
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * Created by imaxct on 17-9-21.
 */
interface LogRepository : JpaRepository<Log, Long> {
    @Query("from Log l where l.timestmp >= :s and l.timestmp<= :t")
    fun getLogByTime(@Param("s") s: Long, @Param("t") t: Long): List<Log>
}