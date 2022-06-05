package com.example.turingenglish.activity.index

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityOptionsCompat
import com.example.turingenglish.BaseActivity
import com.example.turingenglish.MyApplication
import com.example.turingenglish.R
import com.example.turingenglish.activity.load.LoadWordActivity
import com.example.turingenglish.databinding.ActivityVocabularyBinding
import org.litepal.LitePal
import kotlin.properties.Delegates

class VocabularyActivity : BaseActivity(), View.OnClickListener {
    private lateinit var mBinding: ActivityVocabularyBinding
    private lateinit var cardStart: CardView
    private lateinit var cardSearch: CardView
    private lateinit var imgRefresh: ImageView
    private lateinit var imgSearch: ImageView
    private lateinit var imgFlag: ImageView
    private lateinit var tranView: View
    private lateinit var tranSearchView: View
    private lateinit var textStart: TextView
    private lateinit var layoutFiles: RelativeLayout
    private lateinit var textWord: TextView
    private lateinit var textMean: TextView
    private lateinit var textWordNum: TextView
    private lateinit var textBook: TextView
    private lateinit var textDate: TextView
    private lateinit var textMonth: TextView
    private var preparedData = 0
    private var currentRandomId by Delegates.notNull<Int>()
    private var currentBookId by Delegates.notNull<Int>()
    private var isOnClick = true
    private val TAG = "VocabularyActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setContentView(mBinding.root)
        setTransparent(window, mBinding.toolbar)
        addReturnButton()

    }

    private fun initView() {
        mBinding = ActivityVocabularyBinding.inflate(layoutInflater)
        setSupportActionBar(mBinding.toolbar)
        cardStart = mBinding.cardIndexStart
        cardStart.setOnClickListener(this)
        cardSearch = mBinding.cardMainSearch
        cardSearch.setOnClickListener(this)
        imgRefresh = mBinding.imgRefresh
        imgRefresh.setOnClickListener(this)
        imgSearch = mBinding.imgReviewSearch
        imgFlag = mBinding.imgTopFlag
        imgFlag.setOnClickListener(this)
        tranView = mBinding.viewMainTran
        tranSearchView = mBinding.viewSearchTran
        textStart = mBinding.textMainStart
        textStart.setOnClickListener(this)
        layoutFiles = mBinding.layoutMainWords
        layoutFiles.setOnClickListener(this)
        textWord = mBinding.textMainShowWord
        textMean = mBinding.textMainShowWordMean
        textMean.setOnClickListener(this)
        textWordNum = mBinding.textMainShowWordNum
        textBook = mBinding.textMainShowBookName
        textDate = mBinding.textMainDate
        textMonth = mBinding.textMainMonth
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.img_refresh -> {
                    // 旋转动画
                    val animation = RotateAnimation(
                        0.0f, 360.0f,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
                    )
                    animation.duration = 700
                    animation.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation) {}
                        override fun onAnimationEnd(animation: Animation) {
                            //setRandomWord()
                        }

                        override fun onAnimationRepeat(animation: Animation) {}
                    })
                    imgRefresh.startAnimation(animation)
                }
                R.id.text_main_start -> if (isOnClick) {
                    val mIntent = Intent(this, LoadWordActivity::class.java)
                    mIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(
                        mIntent,
                        ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
                    )
                    isOnClick = false
                }
                R.id.img_top_flag -> {

                }
                R.id.text_main_show_word_mean -> {

                }
                R.id.card_main_search -> {

                }
                R.id.layout_main_words -> {

                }
            }
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.vocabulary_menu, menu)
        return true
    }

    private fun addReturnButton() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }


}
