package me.maxct.academic.repository

import me.maxct.academic.entity.Major
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by imaxct on 17-8-30.
 */
@Repository
interface MajorRepository : JpaRepository<Major, Long>