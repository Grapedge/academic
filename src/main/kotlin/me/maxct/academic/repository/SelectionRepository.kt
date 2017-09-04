package me.maxct.academic.repository

import me.maxct.academic.entity.Selection
import me.maxct.academic.entity.SelectionId
import me.maxct.academic.entity.Semester
import me.maxct.academic.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * Created by imaxct on 17-8-30.
 */
@Repository
interface SelectionRepository : JpaRepository<Selection, SelectionId> {
    @Query("from Selection where id.user=:user")
    fun getSelectionByUser(@Param("user") user: User): List<Selection?>

    @Query("from Selection where id.user=:user and id.course.semester=:semester")
    fun getSelectionBySemesterAndUser(@Param("semester") semester: Semester, @Param("user") user: User): List<Selection?>

    @Modifying(clearAutomatically = true)
    @Query("update Selection s set s.score=:score where s.id=:id and s.score<0")
    fun updateSelectionScore(@Param("id") id: SelectionId, @Param("score") score: Double): Int
}