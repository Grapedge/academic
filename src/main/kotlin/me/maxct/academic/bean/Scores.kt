package me.maxct.academic.bean

import java.io.Serializable

/**
 * Created by imaxct on 17-9-18.
 */
class Scores : Serializable{
    var cid: Long = 0
    var list: List<Score> = ArrayList()
}

class Score: Serializable {
    var stuNo: String = ""
    var credit: Double = -1.0
}