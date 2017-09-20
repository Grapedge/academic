package me.maxct.academic.bean

import java.time.LocalDate

/**
 * Created by imaxct on 17-9-20.
 */
class Information {
    var list: List<Info> = ArrayList()
}

open class Info {
    var id: Long? = null
    var name: String? = null
    var idNo: String? = null
    var gender: String? = null
    var unit: String? = null
    var workNo: String? = null
    var address: String? = null
    var birthday: LocalDate? = null
}