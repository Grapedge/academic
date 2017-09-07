package me.maxct.academic.bean

/**
 * Created by imaxct on 17-9-7.
 */
class NetMsg {
    var list: MutableMap<String, Any?> = HashMap()

    fun put(key: String, value: Any?): NetMsg {
        list.put(key, value)
        return this
    }
}