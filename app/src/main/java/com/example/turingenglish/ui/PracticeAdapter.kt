package com.example.turingenglish.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.turingenglish.R
import com.example.turingenglish.model.Practice

/**
 * Created by Ethan On 2022/5/13 20:40
 * God bless my code!
 */
class PracticeAdapter(private val context: Context, private val practiceList:List<Practice>) :RecyclerView.Adapter<PracticeAdapter.ViewHolder>() {
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val practiceImage :ImageView = view.findViewById(R.id.practiceImage)
        val practiceName : TextView = view.findViewById(R.id.practiceName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.practice_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val practice = practiceList[position]
        holder.practiceName.text = practice.name
        Glide.with(context).load(practice.imageId).into(holder.practiceImage)
    }

    override fun getItemCount() = practiceList.size

}