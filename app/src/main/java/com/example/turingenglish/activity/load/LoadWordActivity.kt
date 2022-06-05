package com.example.turingenglish.activity.load

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.turingenglish.BaseActivity
import com.example.turingenglish.MyApplication
import com.example.turingenglish.R
import com.example.turingenglish.activity.LearnWordActivity
import com.example.turingenglish.config.ConfigData
import com.example.turingenglish.database.UserConfig
import com.example.turingenglish.databinding.ActivityLoadWordBinding
import com.example.turingenglish.util.TimeController
import org.litepal.LitePal
import kotlin.concurrent.thread

class LoadWordActivity : BaseActivity() {
    private lateinit var mBinding: ActivityLoadWordBinding

    private var imgLoading: ImageView? = null

    private var progressBar: ProgressBar? = null

    var progressRate = 0

    private var mRunnable: Runnable? = null

    private var mHandler: Handler? = null

    private var mThread: Thread ?= null

    private var TAG = "WaitActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()

        setTransparent(window,null)

        setContentView(mBinding.root)

        Glide.with(this).load(R.drawable.pic_load).into(imgLoading!!)

        //准备数据和设置加载页面
        prepareData()

    }

    private fun prepareData() {
        //开启线程准备数据
        //要获取用户上一次学习的单词和将要学习的单词
        mThread = thread{
            run {
                val userConfigs =
                    LitePal.where("userId = ?", ConfigData.getSinaNumLogged().toString() + "").find(
                        UserConfig::class.java
                    )
//                WordController.generateDailyLearnWords(userConfigs[0].lastStartTime)
//                WordController.generateDailyReviewWords()
//                WordController.wordReviewNum = WordController.needReviewWords.size
                val userConfig = UserConfig()
                userConfig.lastStartTime = TimeController.getCurrentTimeStamp()
                TimeController.todayDate = TimeController.getCurrentDateStamp()
                LearnWordActivity.lastWordMean = ""
                LearnWordActivity.lastWord = ""
                userConfig.updateAll("userId = ?", ConfigData.getSinaNumLogged().toString() + "")
            }
        }

        mThread!!.join()

        mHandler =Handler(Looper.getMainLooper())

          mRunnable = object : Runnable {
            override fun run() {

                //每隔20微秒循环执行run方法,postDelayed方法让run方法隔一定时间执行一次
                mHandler!!.postDelayed(this, 20)
                progressBar!!.progress = ++progressRate
                if (progressRate == 100) {
                    stopTime()
                    val isFirst = ConfigData.getIsFirst()
                    if (true)//如果不是第一次进入，就进入单词学习界面，这里懒得处理了
                    {
                        val mIntent = Intent(this@LoadWordActivity, LearnWordActivity::class.java)
                        /**
                         * ActivityOptions设置转场动画
                         * makeSceneTransitionAnimation
                         * 两个Activity的两个View协同完成转场，也就是原Activity的View过度到新Activity的View
                         * 这里没有实现更厉害的效果，只是平滑地过渡
                         */
                        startActivity(mIntent,
                            ActivityOptions.makeSceneTransitionAnimation(this@LoadWordActivity)
                                .toBundle()
                        )
                    }
                    else//如果是第一次进入，应该进入选择单词书界面，暂不实现
                    {

                    }

                }
            }
         }
        //120微秒后执行mRunnable，留足空间加载数据
        mHandler!!.postDelayed(mRunnable!!, 120)

    }



    private fun initView() {
        mBinding = ActivityLoadWordBinding.inflate(layoutInflater)
        imgLoading = mBinding.imgLoading
        progressBar = mBinding.progressWait
    }

    // 停止计时器
        private fun stopTime() {
        mRunnable?.let { mHandler?.removeCallbacks(it) }
    }

    override fun onBackPressed() {
        finish()
    }

}