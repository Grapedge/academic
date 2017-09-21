package me.maxct.academic.bean

import java.io.Serializable

/**
 * Created by imaxct on 17-9-21.
 */
data class EventLog(
    val id: Long? = null,
    val username: String? = null,
    val uri: String? = null,
    val type: String? = null,
    val name: String? = null,
    val ip: String? = null,
    val args: String? = null,
    val timestamp: Long? = null
) : Serializable