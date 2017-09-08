package me.maxct.academic

import me.maxct.academic.util.StringUtil
import org.junit.Test

/**
 * Created by imaxct on 17-8-30.
 */
class UtilTest {

    @Test
    fun testUtil() {
        println(StringUtil.getSemester())
    }

    @Test
    fun testString() {
        val s = "ROLE_USER,ROLE_ADMIN,ROLE_SYSTEM"
        val x = s.split(',')
        println(x)
        println(x.joinToString(separator = ","))
    }

    @Test
    fun testWeek() {
        println(StringUtil.readWeek("0000111100001111"))
        println(StringUtil.readWeek("0101010111100000"))
    }
}