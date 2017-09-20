package me.maxct.academic.bean

import java.time.LocalDate

/**
 * Created by imaxct on 17-9-20.
 */
class Profiles {
    var teacher: Boolean = false
    var list = ArrayList<Pro>()
}

class Pro {
    var name: String? = null
    var idNo: String? = null
    var gender: String? = null
    var major: Long? = null
    var birthday: LocalDate? = null
    var unit: String? = null
    var workNo: String? = null
    var address: String? = null
}