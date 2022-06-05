package com.example.turingenglish.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.turingenglish.MyApplication
import com.example.turingenglish.R
import com.example.turingenglish.config.ConfigData
import com.example.turingenglish.listener.OnWordMeanClickListener
import com.example.turingenglish.model.ItemWordMeanChoice

/**
 * Created by Ethan On 2022/5/19 15:39
 * God bless my code!
 */
class MeanChoiceAdapter(
    private val context: Context,
    private val mItemWordMeanChoiceList: List<ItemWordMeanChoice>
) : RecyclerView.Adapter<MeanChoiceAdapter.ViewHolder>(), View.OnClickListener {

    var mRecyclerView: RecyclerView? = null

    //创建点击接口对象
    private lateinit var mOnWordMeanClickListener: OnWordMeanClickListener

    //提供设置接口的方法
    fun setOnItemClickListener(listener: OnWordMeanClickListener) {
        //设置一个监听，就是要设置一个接口，一个回调的接口
        mOnWordMeanClickListener = listener
    }

    // 将RecyclerView附加到Adapter上
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null
    }

    override fun onClick(v: View?) {
        if (mRecyclerView != null && v != null) {
            val position = mRecyclerView!!.getChildAdapterPosition(v)
            if (mOnWordMeanClickListener != null && position != null) {
                mOnWordMeanClickListener.onItemClick(
                    mRecyclerView, v, position,
                    mItemWordMeanChoiceList[position]
                )
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardMean: CardView = itemView.findViewById(R.id.item_card_word_choice)
        val imgChoice: ImageView = itemView.findViewById(R.id.item_img_word_choice_status)
        val textWordMean: TextView = itemView.findViewById(R.id.item_text_word_means)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_word_mean_choice, parent, false)
        view.setOnClickListener(this)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemWordMeanChoice: ItemWordMeanChoice = mItemWordMeanChoiceList[position]
        holder.textWordMean.text = itemWordMeanChoice.wordMean
        holder.imgChoice.visibility = View.GONE
        holder.textWordMean.setTextColor(MyApplication.context.resources.getColor(R.color.colorLightBlack))
        if (itemWordMeanChoice.isRight == ItemWordMeanChoice.WRONG) {
            // 说明答错了
            if (ConfigData.getIsNight()) holder.cardMean.setCardBackgroundColor(
                MyApplication.context.resources.getColor(R.color.colorLittleRedN)
            ) else holder.cardMean.setCardBackgroundColor(
                MyApplication.context.resources.getColor(R.color.colorLittleRed)
            )
            holder.imgChoice.visibility = View.VISIBLE
            Glide.with(MyApplication.context).load(R.drawable.icon_wrong)
                .into(holder.imgChoice)
            if (ConfigData.getIsNight()) holder.textWordMean.setTextColor(
                MyApplication.context.resources.getColor(R.color.colorLightRedN)
            ) else holder.textWordMean.setTextColor(
                MyApplication.context.resources.getColor(R.color.colorLightRed)
            )
        } else if (itemWordMeanChoice.isRight === ItemWordMeanChoice.RIGHT) {
            // 说明答对了
            if (ConfigData.getIsNight()) holder.cardMean.setCardBackgroundColor(
                MyApplication.context.resources.getColor(R.color.colorLittleBlueN)
            ) else holder.cardMean.setCardBackgroundColor(
                MyApplication.context.resources.getColor(R.color.colorLittleBlue)
            )
            holder.imgChoice.visibility = View.VISIBLE
            Glide.with(MyApplication.context).load(R.drawable.icon_select_blue)
                .into(holder.imgChoice)
            if (ConfigData.getIsNight()) holder.textWordMean.setTextColor(
                MyApplication.context.resources.getColor(R.color.colorLightBlueN)
            ) else holder.textWordMean.setTextColor(
                MyApplication.context.resources.getColor(R.color.colorLightBlue)
            )
        } else if (itemWordMeanChoice.isRight === ItemWordMeanChoice.NOTSTART) {
            if (ConfigData.getIsNight()) holder.cardMean.setCardBackgroundColor(
                MyApplication.context.resources.getColor(R.color.colorBgWhiteN)
            ) else holder.cardMean.setCardBackgroundColor(
                MyApplication.context.resources.getColor(R.color.colorBgWhite)
            )
            holder.imgChoice.visibility = View.GONE
            if (ConfigData.getIsNight()) holder.textWordMean.setTextColor(
                MyApplication.context.resources.getColor(R.color.colorLightBlackN)
            ) else holder.textWordMean.setTextColor(
                MyApplication.context.resources.getColor(R.color.colorLightBlack)
            )
        }

    }
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}