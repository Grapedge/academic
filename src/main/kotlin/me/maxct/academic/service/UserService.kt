package me.maxct.academic.service

import me.maxct.academic.bean.Msg

interface UserService {
    fun register(): Msg<*>

    fun login(): Msg<*>
}