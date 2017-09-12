package me.maxct.academic.repository

import me.maxct.academic.entity.Setting
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by imaxct on 17-9-12.
 */
interface SettingRepository : JpaRepository<Setting, String>