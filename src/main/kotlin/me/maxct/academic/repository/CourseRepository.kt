package me.maxct.academic.repository

import me.maxct.academic.entity.Course
import me.maxct.academic.entity.CourseId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * Created by imaxct on 17-8-30.
 */
@Repository
interface CourseRepository : JpaRepository<Course, CourseId> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Course c set c.remaining = c.remaining-1 where c.id=:courseId and c.remaining > 0")
    fun decreaseCourseRemaining(@Param("courseId") courseId: CourseId): Int

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Course c set c.remaining = c.remaining-1 where c.id=:courseId and c.remaining < c.total")
    fun increaseCourseRemaining(@Param("courseId") courseId: CourseId): Int
}