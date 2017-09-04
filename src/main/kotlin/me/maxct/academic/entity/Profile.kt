package me.maxct.academic.entity

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

/**
 * 用户资料
 * @param id 用户id
 * @param name 姓名
 * @param idNo 身份证
 * @param gender 性别
 * @param birthday 生日
 * @param unit 单位
 * @param workNo 学工号
 * @param address 地址
 * @param major 专业
 */
@Entity
@Table(name = "ACA_profile")
data class Profile(
    @Id
    @GeneratedValue
    val id: Long? = null,

    @Column(length = 30)
    val name: String? = null,

    @Column(length = 20)
    val idNo: String? = null,

    @Column(length = 4)
    val gender: String = "未知",

    val birthday: LocalDate? = null,

    @Column(length = 200)
    val unit: String? = null,

    @Column(length = 30)
    val workNo: String? = null,

    @Column(columnDefinition = "TEXT")
    val address: String? = null,

    @ManyToOne
    @JoinColumn
    val major: Major? = null
) : Serializable {
    private constructor() : this(gender = "未知")
}