package me.maxct.academic.repository

import me.maxct.academic.entity.Course
import me.maxct.academic.entity.CourseId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

/**
 * Created by imaxct on 17-8-30.
 */
@Repository
interface CourseRepository : JpaRepository<Course, CourseId> {

    @Query("UPDATE Course c set remaining = remaining-1 where id=:courseId and remaining > 0")
    fun updateCourse(courseId: CourseId)
}