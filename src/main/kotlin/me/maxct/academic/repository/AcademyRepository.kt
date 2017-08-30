package me.maxct.academic.repository

import me.maxct.academic.entity.Academy
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by imaxct on 17-8-30.
 */
@Repository
interface AcademyRepository : JpaRepository<Academy, Long>