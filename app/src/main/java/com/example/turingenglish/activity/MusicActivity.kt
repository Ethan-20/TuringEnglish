package com.example.turingenglish.activity

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextSwitcher
import android.widget.TextView
import com.example.turingenglish.BaseActivity
import com.example.turingenglish.MyApplication
import com.example.turingenglish.R
import com.example.turingenglish.databinding.ActivityMusicBinding
import com.example.turingenglish.interfaces.IPlayerControl
import com.example.turingenglish.interfaces.IPlayerControl.Companion.PLAY_STATE_PAUSE
import com.example.turingenglish.interfaces.IPlayerControl.Companion.PLAY_STATE_PLAY
import com.example.turingenglish.interfaces.IPlayerViewControl
import com.example.turingenglish.receiver.NetWorkStateReceiver
import com.example.turingenglish.service.PlayerService

class MusicActivity : BaseActivity() {
    private lateinit var mBinding: ActivityMusicBinding
    private lateinit var musicName: TextView
    private lateinit var musicArtist: TextView
    private lateinit var discImg: ImageView
    private lateinit var seekBar: SeekBar
    private lateinit var previousPlay: ImageView
    private lateinit var playOrStop: ImageView
    private lateinit var nextPlay: ImageView
    private  var mPlayerConnection: PlayerConnection ?=null
    private  var mIPlayerControl: IPlayerControl ? = null
    private var isUserTouchProgressBar = false
    private lateinit var  discAnimation:ObjectAnimator
    private lateinit var  mSwitcher: TextSwitcher

     val TAG = "MusicActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化控件
        initView()
        //启动播放的服务
        initService()
        //绑定服务
        initBindService()
        //初始化服务
        initEvent()
        setContentView(mBinding.root)
        setTransparent(window, mBinding.toolbar)
        //设置动画
        setAnimations()
    }

    //动态注册广播
    override fun onResume() {
        registerReceiver()
        super.onResume()
    }

    //注销广播
    override fun onPause() {
        unregisterReceiver()
        super.onPause()

    }

    //控制UI的接口实例
    private val mIPlayerViewControl = object : IPlayerViewControl {
        override fun onPlayerStateChange(state: Int) {
            //根据播放状态来修改UI
            when (state) {
                PLAY_STATE_PLAY ->
                {
                    //开始播放
                    playOrStop.setImageResource(R.drawable.stop_circle)
                    //TODO 改变调用动画的方法，解决动画不连贯问题
                    discAnimation.start()
                }

                PLAY_STATE_PAUSE ->
                {
                    //开始暂停
                    playOrStop.setImageResource(R.drawable.play_circle)
                    discAnimation.pause()
                    val valueAvatar = discAnimation.animatedValue as Float
                    discAnimation.setFloatValues(valueAvatar,360F+valueAvatar)
                }

            }
        }

        override fun onSeekChange(seek: Int) {
            //改变播放进度，有一个条件，当用户的手触摸到进度条的时候就不更新（否则一边更新，用户的手也在一边拖动，就会造成抖动的现象）
            //通过Log可以发现这里不是主线程，为什么更新进度不会崩溃呢？
            //因为在安卓里面有两个控件是自动用子线程去更新的
            //一个是progressBar,另一个是surfaceView
            if (!isUserTouchProgressBar) {
                seekBar.progress = seek
            }
        }
    }

    //服务连接
    inner class PlayerConnection : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mIPlayerControl = service as IPlayerControl
            mIPlayerControl!!.registerViewController(mIPlayerViewControl)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mIPlayerControl = null
        }
    }

    //    绑定服务
    private fun initBindService() {
        val intent = Intent(this, PlayerService::class.java)
        if (mPlayerConnection==null) {
            mPlayerConnection = PlayerConnection()
        }
        bindService(intent, mPlayerConnection!!, BIND_AUTO_CREATE)
    }

    private fun initService() {
        startService(Intent(this, PlayerService::class.java))
    }

    //    初始化点击事件，拖拽事件等
    private fun initEvent() {
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                //进度条发生改变
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                //手开始触摸去拖动
                isUserTouchProgressBar = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                val touchProgress = seekBar.progress
                //停止拖动
                mIPlayerControl?.seekTo(touchProgress)
                isUserTouchProgressBar = false
            }
        })

        playOrStop.setOnClickListener{
            //播放或暂停  一定要判空
            mIPlayerControl?.playOrPause()
        }

    }

    private fun initView() {
        mBinding = ActivityMusicBinding.inflate(layoutInflater)
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        musicName = mBinding.musicTitle
        musicArtist = mBinding.musicArtist
        discImg = mBinding.disc
        seekBar = mBinding.seekBar
        previousPlay = mBinding.playingPre
        playOrStop = mBinding.playingPlay
        nextPlay = mBinding.playingNext
        mSwitcher = mBinding.textSwitcher
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.vocabulary_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                mIPlayerControl?.stopPlay()
                finish()
            }
        }
        return true
    }



    //动画设置
    @SuppressLint("Recycle")
    private fun setAnimations() {
         discAnimation = ObjectAnimator.ofFloat(discImg, "rotation", 0f, 360f)
        discAnimation.duration = 20000
        discAnimation.interpolator = LinearInterpolator()
        discAnimation.repeatCount = ValueAnimator.INFINITE
        discAnimation.repeatMode = ValueAnimator.RESTART

    }

    override fun onDestroy() {
        super.onDestroy()
        if (mIPlayerControl != null) {
            mIPlayerControl?.UnRegisterViewController(mIPlayerControl!!)
            mPlayerConnection?.let { unbindService(it) }
        }

    }


}