package com.example.turingenglish.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.turingenglish.BaseActivity
import com.example.turingenglish.MyApplication
import com.example.turingenglish.R
import com.example.turingenglish.activity.index.IndexActivity
import com.example.turingenglish.databinding.ActivityLearnWordBinding
import com.example.turingenglish.model.ItemWordMeanChoice
import com.example.turingenglish.ui.adapters.MeanChoiceAdapter

class LearnWordActivity : BaseActivity(), View.OnClickListener {
    private val mBinding = ActivityLearnWordBinding.inflate(layoutInflater)
    private lateinit var recyclerView: RecyclerView

    private lateinit var layoutDelete: RelativeLayout
    private lateinit var layoutVoice: RelativeLayout
    private lateinit var layoutTip: RelativeLayout

    private val wordMeanChoices: List<ItemWordMeanChoice> = ArrayList<ItemWordMeanChoice>()

    private lateinit var layoutBottomReview: LinearLayout

    private lateinit var layoutBottomLearn: LinearLayout

    private lateinit var textWord: TextView
    private lateinit var textWordPhone: TextView

    private lateinit var textLastWord: TextView
    private lateinit var textLastWordMean: TextView

    private lateinit var randomId: IntArray

    private lateinit var cardKnow: RelativeLayout

    private lateinit var cardNotKnow: RelativeLayout
    private lateinit var cardDark: RelativeLayout

    private lateinit var cardTip: CardView

    private lateinit var textTip: TextView


    private lateinit var textLearnNum: TextView
    private lateinit var textReviewNum: TextView

    private lateinit var meanChoiceAdapter: MeanChoiceAdapter

     val TAG = "LearnWordActivity"

    var needUpdate = true

    // 学习时间记录
    private val startTime: Long = -1

    val MODE_NAME = "learnmode"

    val MODE_GENERAL = 1
    val MODE_ONCE = 2

    private val currentMode = 0

    companion object{
        // 记录上一个单词
        var lastWord: String? = null
        var lastWordMean: String? = null
    }
    private var tipSentence: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparent(window, null)
        initView()
        setContentView(mBinding.root)

    }

    private fun initView() {

        recyclerView = mBinding.recyclerviewWordMean
        layoutBottomReview = mBinding.layoutWordBottom
        layoutBottomLearn = mBinding.linearLearnControl
        textWord = mBinding.textLearnWord
        textWordPhone = mBinding.textLearnWordPhone
        cardDark = mBinding.cardDark
        cardDark.setOnClickListener(this)
        cardKnow = mBinding.cardKnow
        cardKnow.setOnClickListener(this)
        cardNotKnow = mBinding.cardNoKnow
        cardNotKnow.setOnClickListener(this)
        textLearnNum = mBinding.textNewNumTop
        textReviewNum = mBinding.textReviewNumTop
        textLastWord = mBinding.textWordTop
        textLastWordMean = mBinding.textWordTopMean
        layoutTip = mBinding.layoutWordTip
        layoutTip.setOnClickListener(this)
        layoutDelete = mBinding.layoutWordDelete
        layoutDelete.setOnClickListener(this)
        layoutVoice = mBinding.layoutWordVoice
        layoutVoice.setOnClickListener(this)
        cardTip = mBinding.cardLwTip
        textTip = mBinding.textLwTip
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.card_know->{
                val intent = Intent(this,FinishActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this@LearnWordActivity, IndexActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        if (needUpdate) {
            updateStatus()
            needUpdate = false
        }
    }

    private fun updateStatus() {

    }


}