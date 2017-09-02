package me.maxct.academic.security.bean

import me.maxct.academic.entity.User

/**
 * Created by imaxct on 17-9-2.
 */
object JwtUserFactory {

    fun build(user: User): JwtUser =
        JwtUser(id = user.id!!, username = user.username!!, roles = user.roles!!.map{ i -> i.authority })
}