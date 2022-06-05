package com.example.turingenglish.database

import org.litepal.crud.LitePalSupport

/**
 * Created by Ethan On 2022/5/29 9:50
 * God bless my code!
 */
data class Sentence(
    // 英文句子
    var enSentence: String,

    // 中文句子
    var chsSentence: String,

    // 归属单词
    var wordId: Int
) : LitePalSupport()


