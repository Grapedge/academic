package me.maxct.academic.entity

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

/**
 * 奖惩记录
 * @param id id
 * @param user 奖惩对象
 * @param performer 处理者
 * @param timestamp 日期
 * @param reward 是否是奖励
 * @param description 详细描述
 */
@Entity
@Table(name = "ACA_record")
data class Record(
    @Id
    @GeneratedValue
    val id: Long? = null,

    @ManyToOne
    @JoinColumn
    val user: User? = null,

    @ManyToOne
    @JoinColumn
    val performer: User? = null,

    @Column(name = "record_time")
    val timestamp: LocalDate = LocalDate.now(),

    val reward: Boolean = false,

    @Column(length = 500)
    val description: String? = null
) : Serializable {
    private constructor() : this(
        id = null, user = null, performer = null, timestamp = LocalDate.now(), reward = false, description = null
    )
}