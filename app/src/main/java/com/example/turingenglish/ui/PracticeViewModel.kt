package com.example.turingenglish.ui

import androidx.lifecycle.ViewModel
import com.example.turingenglish.Constants
import com.example.turingenglish.R
import com.example.turingenglish.model.Practice

/**
 * Created by Ethan On 2022/5/13 20:56
 * God bless my code!
 */
class PracticeViewModel :ViewModel(){
    companion object{
        val practices = mutableListOf(Practice("背单词", R.drawable.apple, Constants.reciteWords),Practice("音乐模式", R.drawable.banana,Constants.Reading),Practice("句子跟读", R.drawable.cherry,Constants.followingSentences)
            ,Practice("翻译", R.drawable.orange,Constants.translating),Practice("Talk To Bot", R.drawable.grape,Constants.talkToBot),Practice("Coming Soon", R.drawable.mango,Constants.comingSoon))
    }
}