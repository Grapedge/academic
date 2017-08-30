package me.maxct.academic.repository

import me.maxct.academic.entity.Selection
import me.maxct.academic.entity.SelectionId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by imaxct on 17-8-30.
 */
@Repository
interface SelectionRepository : JpaRepository<Selection, SelectionId>