package me.maxct.academic.repository

import me.maxct.academic.entity.Course
import me.maxct.academic.entity.CourseId
import me.maxct.academic.entity.Semester
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * Created by imaxct on 17-8-30.
 */
@Repository
@CacheConfig(cacheNames = arrayOf("courses"))
interface CourseRepository : JpaRepository<Course, CourseId> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Course c set c.remaining = c.remaining-1 where c.id=:courseId and c.remaining > 0") //TODO
    fun decreaseCourseRemaining(@Param("courseId") courseId: CourseId): Long

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Course c set c.remaining = c.remaining-1 where c.id=:courseId and c.remaining < c.total") //TODO
    fun increaseCourseRemaining(@Param("courseId") courseId: CourseId): Long

    fun deleteById(id: CourseId): Long

    @Cacheable
    @Query("from Course c where c.id.semester = :s")
    fun getCourseBySemester(@Param("s") semester: Semester): List<Course?>
}