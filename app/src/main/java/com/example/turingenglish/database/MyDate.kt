package com.example.turingenglish.database

import org.litepal.annotation.Column
import org.litepal.crud.LitePalSupport

/**
 * Created by Ethan On 2022/5/29 9:50
 * God bless my code!
 */
data class MyDate(
    @Column(unique = true)
     var id: Int ,

    // 年
    var year: Int ,

    // 月

    var month: Int ,

    // 日

    var date: Int ,

    // 在这一天新学多少单词

    var wordLearnNumber: Int ,

    // 在这一天复习多少单词

    var wordReviewNumber: Int ,

    // 在这一天的心情感悟

    var remark: String ,

    // 归属用户

    var userId :Int
) : LitePalSupport()


