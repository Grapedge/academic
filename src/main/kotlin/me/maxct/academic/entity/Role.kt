package me.maxct.academic.entity

import org.springframework.security.core.GrantedAuthority
import java.io.Serializable
import javax.persistence.*

/**
 * 权限实体
 * @param id id
 * @param name 名称
 */
@Entity
@Table(name = "ACA_role")
data class Role(
    @Id
    @GeneratedValue
    val id: Long? = null,
    @Column(length = 10)
    val name: String? = null
) : GrantedAuthority {
    override fun getAuthority() = "ROLE_$name"

    private constructor() : this(null, null)
}