package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Table

/**
 * 选课记录
 * @param id 选课的复合id
 * @param score 当前课的分数
 */
@Entity
@Table(name = "ACA_selection")
data class Selection(
    val id: SelectionId? = null,
    val score: Double = 0.0
) : Serializable {
    private constructor() : this(score = 0.0)
}