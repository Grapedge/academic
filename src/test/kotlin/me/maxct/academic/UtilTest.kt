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

    @Test
    fun testCourse(){
        val a = "001111111111110000"
        val b = "001111110011110000"
        println(Integer.parseInt(a.reversed(), 2))
        println(Integer.parseInt(b.reversed(), 2))

        val c = "00001111111000000"
        val d = "111100010001111"
        val e = Integer.parseInt(c.reversed(), 2)
        val f = Integer.parseInt(d.reversed(), 2)
        println(e)
        println(f)
        println(e.and(f))
    }
}