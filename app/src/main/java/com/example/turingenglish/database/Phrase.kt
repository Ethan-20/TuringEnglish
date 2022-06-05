package com.example.turingenglish.database

import org.litepal.crud.LitePalSupport

/**
 * Created by Ethan On 2022/5/29 9:50
 * God bless my code!
 */
data class Phrase(
    // 中文短语
    var chsPhrase: String,

    // 英语短语

    var enPhrase: String ,

    // 归属单词

    var wordId :Int
) : LitePalSupport()


