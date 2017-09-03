package me.maxct.academic.repository

import me.maxct.academic.entity.Semester
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

/**
 * Created by imaxct on 17-8-30.
 */
@Repository
interface SemesterRepository : JpaRepository<Semester, Long> {

    @Query("from Semester where CURRENT_DATE() between beginDate and endDate")
    fun getCurrentSemester(): Semester

    @Query("select count(*)>0 from Semester i" +
        "where ( :s between i.beginDate and i.endDate) or ( :t between i.beginDate and i.endDate)")
    fun existSemester(@Param("s") beginDate: LocalDate, @Param("t") endDate: LocalDate): Boolean
}