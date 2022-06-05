package com.example.turingenglish.util

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Ethan On 2022/5/22 16:24
 * God bless my code!
 */
object TimeController{
        val FORMAT_NOTIME = "yyyy-MM-dd"

        var simpleDateFormat = SimpleDateFormat(FORMAT_NOTIME)

        var todayDate: Long = 0

        private val TAG = "TimeController"

        /*----日期类----*/
        // 得到当前日期戳(不带时间，只有日期)
        fun getCurrentDateStamp(): Long {
            val cal = Calendar.getInstance()
            val currentYear = cal[Calendar.YEAR]
            val currentMonth = cal[Calendar.MONTH] + 1
            val currentDate = cal[Calendar.DATE]
            var time: Long = 0
            try {
                time = simpleDateFormat.parse("$currentYear-$currentMonth-$currentDate").time
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            Log.d(TAG, "getCurrentDateStamp: $time")
            return time
        }

    // 根据指定日期戳解析成日期形式（yyyy-MM-dd）
    fun getStringDate(timeStamp: Long): String {
        return simpleDateFormat.format(Date(timeStamp.toString().toLong()))
    }

    // 根据指定日期戳解析成日期形式（yyyy-MM-dd）
    fun getStringDateDetail(timeStamp: Long): String? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return simpleDateFormat.format(Date(timeStamp.toString().toLong()))
    }

    // 得到当前日期的指定间隔后的日期
    @Throws(ParseException::class)
    fun getDateByDays(time: Long, intervalDay: Int): Long {
        // 转换成Calendar
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time
        calendar.add(Calendar.DATE, intervalDay)
        return calendar.timeInMillis
    }

    // 返回两个日期之间相隔多少天
    @Throws(ParseException::class)
    fun daysInternal(time1: Long, time2: Long): Int {
        val date1 = simpleDateFormat.parse(getStringDate(time1))
        val date2 = simpleDateFormat.parse(getStringDate(time2))
        return ((date2.time - date1.time) / (1000 * 3600 * 24)).toInt()
    }

    /*----时间类----*/

    /*----时间类----*/ // 得到当前时间戳（有日期与时间）
    fun getCurrentTimeStamp(): Long {
        return System.currentTimeMillis()
    }

    // 判断两个时间戳是否为同一天
    fun isTheSameDay(time1: Long, time2: Long): Boolean {
        return getStringDate(time1) == getStringDate(time2)
    }

    // 返回过去第几天的日期
    fun getPastDate(past: Int): String? {
        val calendar = Calendar.getInstance()
        calendar[Calendar.DAY_OF_YEAR] = calendar[Calendar.DAY_OF_YEAR] - past
        val today = calendar.time
        val format = SimpleDateFormat("MM-dd")
        return format.format(today)
    }

    // 返回过去第几天的日期（有年份）
    fun getPastDateWithYear(past: Int): String? {
        val calendar = Calendar.getInstance()
        calendar[Calendar.DAY_OF_YEAR] = calendar[Calendar.DAY_OF_YEAR] - past
        val today = calendar.time
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(today)
    }

    // 获取n天以后的日期
    fun getDayAgoOrAfterString(num: Int): String? {
        val calendar1 = Calendar.getInstance()
        val sdf1 = SimpleDateFormat("yyyy年MM月dd日")
        calendar1.add(Calendar.DATE, num)
        return sdf1.format(calendar1.time)
    }
    }
