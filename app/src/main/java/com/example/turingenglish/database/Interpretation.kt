package com.example.turingenglish.database

import org.litepal.crud.LitePalSupport

/**
 * Created by Ethan On 2022/5/29 9:50
 * God bless my code!
 */
data class Interpretation(
     // 词性
    var wordType: String,

    // 中文词意

    var CHSMeaning: String,

    // 英文词意

    var ENMeaning: String,

    // 归属单词

    var wordId: Int
) : LitePalSupport()


