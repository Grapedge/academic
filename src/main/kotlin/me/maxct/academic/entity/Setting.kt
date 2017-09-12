package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by imaxct on 17-9-12.
 */
@Entity
@Table(name = "ACA_setting")
data class Setting(
    @Id
    @Column(length = 50)
    val name: String? = null,
    @Column(columnDefinition = "TEXT default null")
    val value: String? = null
) : Serializable {
    private constructor() : this(name = null, value = null)
}