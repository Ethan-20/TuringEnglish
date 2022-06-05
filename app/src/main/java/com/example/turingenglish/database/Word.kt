package com.example.turingenglish.database

import org.litepal.annotation.Column
import org.litepal.crud.LitePalSupport

/**
 * Created by Ethan On 2022/5/29 10:07
 * God bless my code!
 */
data class Word(
    // ID
    @Column(unique = true)
    var wordId: Int,

    // 单词
    var word: String,

    // 英国音标
    var ukPhone: String,

    // 美国音标
    var usPhone: String,

    // 巧记
    val remMethod: String,

    // 图片(网址)
    val picAddress: String,

    // 自定义照片
    var picCustom: ByteArray,

    // 自定义备注
    var remark: String,

    // 设置归属
    var belongBook: String,

    // 是否收藏
    @Column(defaultValue = "0")
    var isCollected: Int,

    // 是否是简单词
    @Column(defaultValue = "0")
    var isEasy: Int = 0,

// 是否是刚学过
    @Column(defaultValue = "0")
    var justLearned: Int,

/*
     * 以下是学习复习专用的
     */

/*
     * 以下是学习复习专用的
     */
// 是否需要学习
    @Column(defaultValue = "0")
    var isNeedLearned: Int,

// 需要学习的时间（以天为单位）
    var needLearnDate: Long,

// 需要复习的时间（以天为单位）
    var needReviewDate: Long,

// 是否学习过
    @Column(defaultValue = "0")
    var isLearned :Int,

// 总计检验次数
    @Column(defaultValue = "0")
    var examNum :Int,

// 总计检验答对次数
    @Column(defaultValue = "0")
var examRightNum :Int,

// 上次已掌握时间（时间戳）
@Column(defaultValue = "0")
var lastMasterTime: Long ,

// 上次复习的时间（时间戳）
@Column(defaultValue = "0")
var lastReviewTime: Long ,

// 掌握程度（总计10分）
@Column(defaultValue = "0")
var masterDegree :Int,

// 深度掌握次数
/*
     * 前提：掌握程度已达到10
     * 当深度次数为0时，记下次复习时间=上次已掌握时间+4天，若及时复习，更新上次已掌握时间
     * 当深度次数为1时，记下次复习时间=上次已掌握时间+3天，若及时复习，更新上次已掌握时间
     * 当深度次数为2时，记下次复习时间=上次已掌握时间+8天，若及时复习，更新上次已掌握时间
     * 当深度次数为3时，记已经完全掌握
     *
     * 检测哪些单词未及时深度复习：
     * 首先单词必须掌握程度=10，其次单词上次掌握的时间与现在的时间进行对比
     * （1）要是深度次数为0，且两者时间之差为大于4天，说明未深度复习
     * （2）要是深度次数为1，且两者时间之差为大于3天，说明未深度复习
     * （3）要是深度次数为2，且两者时间之差为大于8天，说明未深度复习
     * （#）若未及时深度复习，一律将其单词掌握程度-2（10→8）
     *
     * */
@Column(defaultValue = "0")
var deepMasterTimes: Int
) : LitePalSupport()
