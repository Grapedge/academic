package me.maxct.academic.bean

import java.io.Serializable
import java.time.LocalDateTime

/**
 * Created by imaxct on 17-9-21.
 */
class Period : Serializable {
    var s: LocalDateTime? = null
    var t: LocalDateTime? = null
}