package me.maxct.academic.util

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
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

    fun toJson(obj: Any?) : String = objectMapper.writeValueAsString(obj)
}