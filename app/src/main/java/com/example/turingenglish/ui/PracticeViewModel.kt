package com.example.turingenglish.ui

import androidx.lifecycle.ViewModel
import com.example.turingenglish.R
import com.example.turingenglish.model.Practice

/**
 * Created by Ethan On 2022/5/13 20:56
 * God bless my code!
 */
class PracticeViewModel :ViewModel(){
    companion object{
        val practices = mutableListOf(Practice("背单词", R.drawable.apple),Practice("阅读模式", R.drawable.banana),Practice("句子跟读", R.drawable.cherry)
            ,Practice("翻译", R.drawable.orange),Practice("Talk To Bot", R.drawable.grape),Practice("Coming Soon", R.drawable.mango))
        val testPractices = mutableListOf(Practice("背单词", R.drawable.apple),Practice("阅读模式", R.drawable.banana),Practice("句子跟读", R.drawable.cherry)
            ,Practice("翻译", R.drawable.orange),Practice("Talk To Bot", R.drawable.grape),Practice("Coming Soon", R.drawable.mango),Practice("背单词", R.drawable.apple),Practice("阅读模式", R.drawable.banana),Practice("句子跟读", R.drawable.cherry)
            ,Practice("翻译", R.drawable.orange),Practice("Talk To Bot", R.drawable.grape),Practice("Coming Soon", R.drawable.mango),Practice("背单词", R.drawable.apple),Practice("阅读模式", R.drawable.banana),Practice("句子跟读", R.drawable.cherry)
            ,Practice("翻译", R.drawable.orange),Practice("Talk To Bot", R.drawable.grape),Practice("Coming Soon", R.drawable.mango))
    }
}