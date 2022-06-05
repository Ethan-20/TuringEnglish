package com.example.turingenglish.database

import org.litepal.crud.LitePalSupport

/**
 * Created by Ethan On 2022/5/29 9:50
 * God bless my code!
 */
data class DailyData(var id: Int = 0
            ,var picVertical: ByteArray
            ,var picHorizontal: ByteArray
            ,var dailyChs: String
            ,var dailyEn: String
            ,var dailySound: String
            ,var dayTime: String) : LitePalSupport()


