package com.example.turingenglish.database

import org.litepal.annotation.Column
import org.litepal.crud.LitePalSupport

/**
 * Created by Ethan On 2022/5/29 10:01
 * God bless my code!
 */
data class User(
    // 唯一
    @Column(unique = true, defaultValue = "000000")
    var userId : Int,
    // 头像
    var userProfile : String,
    var userName:String,
    // 词汇量
    @Column(defaultValue = "0")
    var userWordNumber: Int,
    // 金币数
    @Column(defaultValue = "0")
    var userMoney :Int

):LitePalSupport()
