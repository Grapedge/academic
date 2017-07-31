package me.maxct.academic.service

import me.maxct.academic.entity.User

interface UserService {
    fun register(): User
}