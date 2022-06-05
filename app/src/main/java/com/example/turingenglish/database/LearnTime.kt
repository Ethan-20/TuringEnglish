package com.example.turingenglish.database

import org.litepal.crud.LitePalSupport

/**
 * Created by Ethan On 2022/5/29 9:50
 * God bless my code!
 */
data class LearnTime(
            // 日期
     var date: String,

            // 学习时间

     var time: String) : LitePalSupport()


