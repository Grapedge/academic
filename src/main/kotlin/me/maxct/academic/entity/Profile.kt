package me.maxct.academic.entity

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Created by imaxct on 17-7-31.
 * academic
 */
@Entity
@Table(name = "ACA_profile")
data class Profile(
        val id: Long? = null,
        val name: String? = null,
        val idNo: String? = null,
        val gender: String = "未知",
        val birthday: LocalDate? = null,
        val unit: String? = null
) : Serializable {
    private constructor() : this(null, null, null, "未知", null, null)
}