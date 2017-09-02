package me.maxct.academic.repository

import me.maxct.academic.entity.Semester
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

/**
 * Created by imaxct on 17-8-30.
 */
@Repository
interface SemesterRepository : JpaRepository<Semester, Long> {

    @Query("from Semester where CURRENT_DATE() between beginDate and endDate")
    fun getCurrentSemester(): Semester
}