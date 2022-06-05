package com.example.turingenglish.database

import org.litepal.annotation.Column
import org.litepal.crud.LitePalSupport

/**
 * Created by Ethan On 2022/5/29 10:16
 * God bless my code!
 */
data class WordFolder(
    @Column(unique = true)
    var id: Int ,

    var createTime: String ,

    var name: String ,

    var remark: String
) : LitePalSupport()
