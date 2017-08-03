package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Table

/**
 * 选课记录
 * Created by imaxct on 17-8-3.
 * academic
 */
@Entity
@Table(name = "ACA_selection")
data class Selection(
    val id: SelectionId? = null,
    val score: Double = 0.0
) : Serializable {
    private constructor() : this(score = 0.0)
}