package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.*

/**
 * 选课记录
 * @param score 当前课的分数
 */
@Entity
@Table(name = "ACA_selection")
data class Selection(
    @Id @Column(length = 40)
    val courseName: String? = null,

    @Id @ManyToOne @JoinColumn
    val academy: Academy? = null,

    @Id @ManyToOne @JoinColumn
    val semester: Semester? = null,

    @Id @ManyToOne @JoinColumn
    val teacher: User? = null,

    @Id @ManyToOne @JoinColumn
    val user: User? = null,

    val score: Double = -1.0
) : Serializable {
    private constructor() : this(score = -1.0)
    constructor(id: SelectionId): this(
        courseName = id.courseName,
        academy = id.academy,
        semester = id.semester,
        teacher = id.teacher,
        user = id.user
    )
    fun getId(): SelectionId = SelectionId(courseName, academy, semester, teacher, user)
}