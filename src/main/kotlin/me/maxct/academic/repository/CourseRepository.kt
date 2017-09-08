package me.maxct.academic.repository

import me.maxct.academic.entity.Course
import me.maxct.academic.entity.Semester
import me.maxct.academic.entity.User
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
interface CourseRepository : JpaRepository<Course, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Course c set c.remaining = c.remaining-1 where c.id=:courseId and c.remaining > 0")
    fun decreaseCourseRemaining(@Param("courseId") courseId: Long): Int

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Course c set c.remaining = c.remaining-1 where c.id=:courseId and c.remaining < c.total")
    fun increaseCourseRemaining(@Param("courseId") courseId: Long): Int

    fun deleteById(id: Long): Long

    @Cacheable
    @Query("from Course c where c.semester = :s")
    fun getCourseBySemester(@Param("s") semester: Semester): List<Course?>

    @Query("from Course c where c.teacher=:u")
    fun getCourseByTeacher(@Param("u") teacher: User): List<Course?>
}