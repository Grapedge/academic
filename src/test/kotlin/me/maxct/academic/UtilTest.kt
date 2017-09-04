package me.maxct.academic

import me.maxct.academic.entity.*
import me.maxct.academic.util.StringUtil
import org.junit.Test
import java.time.LocalDate

/**
 * Created by imaxct on 17-8-30.
 */
class UtilTest {

    @Test
    fun testUtil(){
        println(StringUtil.getSemester())
    }

    @Test
    fun testString(){
        val s = "ROLE_USER,ROLE_ADMIN,ROLE_SYSTEM"
        val x = s.split(',')
        println(x)
        println(x.joinToString(separator = ","))
    }

    @Test
    fun testJson(){
        val c = Course(
            id = CourseId(
                courseName = "aaa",
                academy = Academy(
                    id = 1L, name = "academy"
                ),
                semester = Semester(
                    id = 1L,
                    name = "semester",
                    beginDate = LocalDate.now(),
                    endDate = LocalDate.now().plusDays(1L)
                )
            ),
            teacher = User(
                username = "teacher",
                password = "teacher",
                roles = "ROLE_SYSTEM",
                profile = Profile(
                    id = 10L
                )
            ),
            credit = 100.01,
            week = "00111111110000",
            total = 1000,
            remaining = 1000
        )
        println(StringUtil.toJson(c))
    }
}