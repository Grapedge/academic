package me.maxct.academic.util

import com.fasterxml.jackson.databind.ObjectMapper
import java.time.LocalDate

/**
 * Created by imaxct on 17-8-30.
 */
object StringUtil {
    private val objectMapper: ObjectMapper = ObjectMapper()

    fun getUriName(uri: String, get: Boolean): String =
        when {
        //admin
            uri == "/a/c" -> "获取自己负责的课"
            match(uri, "/a/s/[0-9]{1,}") -> if (get) "查看课的选课情况" else "录入成绩"
            uri == "/a/r" -> if (get) "获取自己操作的奖惩记录" else "添加一条奖惩记录"
            match(uri, "/a/r/[0-9]{1,}") -> "删除奖惩记录"
            uri == "/a/i" -> "查询学生信息"
            uri == "/a/up" -> "批量更新成绩"
        //user
            uri == "/u/ch" -> "获取所有已选课程"
            match(uri, "/u/c/[0-9]{1,}") -> if (get) "获取当前学期开设课程" else "选课"
            uri == "/u/r" -> "获取奖惩记录"
            uri == "/u/i" -> "获取自己的信息"
            match(uri, "/u/d/[0-9]{1,}") -> "退选"
            uri == "/u/s" -> "获取本学期课表"
            uri == "/u/a" -> "获取所有学院"
            uri == "/u/m" -> "获取所有专业"
            uri == "/u/n" -> "获取公告"
            uri == "/u/st" -> "获取学期"
        //system
            uri == "/s/a" -> "创建学院"
            uri == "/s/m" -> "创建专业"
            match(uri, "/s/ua/[0-9]{1,}") -> "更新学院信息"
            match(uri, "/s/um/[0-9]{1,}") -> "更新专业信息"
            match(uri, "/s/i/[0-9]{1,}") -> "获取用户信息"
            uri == "/s/c" -> if (get) "获取所有课程" else "创建课程"
            uri == "/s/s" -> if (get) "获取所有的设置" else "更新设置"
            uri == "/s/info" -> "搜索用户信息"
            uri == "/s/b" -> "批量更新profile"
            uri == "/s/t" -> "获取所有教师"
            uri == "/s/uc" -> "更新课程信息"
            uri == "/s/ac" -> "保存学院信息"
            uri == "/s/ma" -> "保存专业"
            uri == "/s/se" -> "保存学期"
            uri == "/s/bi" -> "批量导入用户"
            uri == "/s/log" -> "查看日志"
            else -> uri
        }

    fun match(str: String, regex: String): Boolean {
        return str.matches(regex.toRegex())
    }

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

    private val WEEK = arrayOf("零", "一", "二", "三", "四", "五", "六", "日")

    fun getWeekDayName(x: Int): String = WEEK[x]
}