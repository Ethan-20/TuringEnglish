package com.example.turingenglish.listener

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.turingenglish.model.ItemWordMeanChoice

/**
 * Created by Ethan On 2022/5/19 16:28
 * God bless my code!
 */
interface OnWordMeanClickListener {
    fun onItemClick(
        parent: RecyclerView?,
        view: View?,
        position: Int,
        itemWordMeanChoice: ItemWordMeanChoice?
    )
}