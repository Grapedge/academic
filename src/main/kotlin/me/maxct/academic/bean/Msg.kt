package me.maxct.academic.bean

import java.io.Serializable

/**
 * Created by imaxct on 17-8-29.
 * academic
 */
data class Msg<out T>(
    val ok: Boolean = false,
    val msg: String = "",
    val obj: T? = null
) : Serializable {
    companion object {
        fun ok(msg: String) = Msg(ok = true, msg = msg, obj = null)
        fun ok(msg: String, obj: Any) = Msg(ok = true, msg = msg, obj = obj)
        fun err(msg: String) = Msg(msg = msg, obj = null)
        fun err(msg: String, obj: Any) = Msg(msg = msg, obj = obj)
    }
}