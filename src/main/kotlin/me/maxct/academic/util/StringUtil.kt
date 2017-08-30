package me.maxct.academic.util

import java.time.LocalDate

/**
 * Created by imaxct on 17-8-30.
 */
class StringUtil {
    companion object {
        fun getSemester(): String {
            val date = LocalDate.now()
            return if (date.isBefore(LocalDate.of(date.year, 9, 1))){
                "${date.year-1}-${date.year}-2"
            }else {
                "${date.year}-${date.year+1}-1"
            }
        }
    }
}