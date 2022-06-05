package com.example.turingenglish.database

import org.litepal.annotation.Column
import org.litepal.crud.LitePalSupport

/**
 * Created by Ethan On 2022/5/29 10:07
 * God bless my code!
 */
data class UserConfig(    @Column(unique = true)
                          var id: Int = 0 ,

                          @Column(defaultValue = "-1")
                          // 当前正在使用的书目
                          // 如果为-1，说明未创建书目，是个新用户
                          var currentBookId: Int = -1,

                          @Column(defaultValue = "-1") // 每日需要背单词的数量
                          // 如果为0，说明未设置单词量
                          var wordNeedReciteNum :Int =-1,

                          // 归属用户
                          var userId :Int = 0,

                          @Column(defaultValue = "0")
                          // 上次开始背单词的时间（点了背单词的按钮的那一刻）
                          // 重新选书时，记得重置这个值
                          var lastStartTime: Long = 0
) :LitePalSupport()

