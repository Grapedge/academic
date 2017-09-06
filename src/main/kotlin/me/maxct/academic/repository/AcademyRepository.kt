package me.maxct.academic.repository

import me.maxct.academic.entity.Academy
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

/**
 * Created by imaxct on 17-8-30.
 */
@Repository
@CacheConfig(cacheNames = arrayOf("academy"))
interface AcademyRepository : JpaRepository<Academy, Long> {
    @Cacheable
    @Query("from Academy")
    fun getAll(): List<Academy?>
}