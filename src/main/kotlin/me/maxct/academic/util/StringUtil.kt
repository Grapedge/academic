package me.maxct.academic.util

import com.fasterxml.jackson.databind.ObjectMapper
import java.time.LocalDate

/**
 * Created by imaxct on 17-8-30.
 */
object StringUtil {
    private val objectMapper: ObjectMapper = ObjectMapper()

    fun getSemester(): String {
        val date = LocalDate.now()
        return if (date.isBefore(LocalDate.of(date.year, 9, 1))) {
            "${date.year - 1}-${date.year}-2"
        } else {
            "${date.year}-${date.year + 1}-1"
        }
    }

    fun toJson(obj: Any?): String = objectMapper.writeValueAsString(obj)

    fun readWeek(str: String): String {
        val group = IntArray(24)
        val size = IntArray(24)
        var cnt = 0
        var i = 0
        while (i < str.length) {
            if (str[i] == '1') {
                group[cnt] = i
                var j = i
                while (j < str.length) {
                    if (str[j] == '1') {
                        size[cnt]++
                    } else {
                        break
                    }
                    j++
                }
                i = j
                cnt++
            } else {
                i++
            }
        }
        var res = ""
        i = 0
        while (i < cnt) {
            res = if (size[i] > 1) {
                "$res${group[i] + 1}-${group[i] + size[i]},"
            } else {
                "$res${group[i] + 1},"
            }
            i++
        }
        return "${res.substring(0, res.length - 1)}周"
    }
}