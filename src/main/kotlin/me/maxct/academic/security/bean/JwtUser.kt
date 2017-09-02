package me.maxct.academic.security.bean

import java.io.Serializable

/**
 * Created by imaxct on 17-9-2.
 */
data class JwtUser(
    val id: Long,
    val username: String,
    val roles: List<String>
) : Serializable