package me.maxct.academic.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by imaxct on 17-8-1.
 * academic
 */
@Entity
@Table(name = "ACA_academy")
data class Academy(
        @Id
        val id: Long? = null,
        @Column(length = 60)
        val name: String? = null
) : Serializable