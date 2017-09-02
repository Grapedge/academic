package me.maxct.academic.exception

/**
 * Created by imaxct on 17-9-2.
 */
class JwtAuthException : RuntimeException {
    constructor(msg: String): super(msg)
}