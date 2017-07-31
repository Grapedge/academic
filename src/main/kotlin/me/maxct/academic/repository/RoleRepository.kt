package me.maxct.academic.repository

import me.maxct.academic.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by imaxct on 17-7-31.
 * academic
 */
@Repository
interface RoleRepository : JpaRepository<Role, Long>