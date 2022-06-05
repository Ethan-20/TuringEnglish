package com.example.turingenglish.model

/**
 * Created by Ethan On 2022/5/19 15:34
 * God bless my code!
 */
data class ItemWordMeanChoice(val id: Int,// ID值，用来与正确的值进行判断
                              val wordMean: String, // 存放内容
                              val isRight: Int// 存放判断状态,-1代表未选，0代表正确，1代表错误
                              ) {
    companion object{
        const val NOTSTART = -1
        const val RIGHT = 0
        const val WRONG = 1
    }
}
