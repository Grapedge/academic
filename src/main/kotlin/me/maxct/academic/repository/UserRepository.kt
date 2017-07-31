package me.maxct.academic.repository

import me.maxct.academic.entity.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by imaxct on 17-7-31.
 * academic
 */
interface UserRepository : JpaRepository<User, Long>