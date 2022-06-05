package com.example.turingenglish.presenter

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.util.Log
import com.example.turingenglish.MyApplication
import com.example.turingenglish.R
import com.example.turingenglish.interfaces.IPlayerControl
import com.example.turingenglish.interfaces.IPlayerControl.Companion.PLAY_STATE_PAUSE
import com.example.turingenglish.interfaces.IPlayerControl.Companion.PLAY_STATE_PLAY
import com.example.turingenglish.interfaces.IPlayerControl.Companion.PLAY_STATE_STOP
import com.example.turingenglish.interfaces.IPlayerViewControl
import java.util.*

/**
 * Created by Ethan On 2022/5/30 10:06
 * God bless my code!
 */

class PlayerPresenter:Binder(),IPlayerControl {
    private val TAG = "PlayerPresenter"
    private var mViewController : IPlayerViewControl ?=null
    private var mCurrentState:Int = PLAY_STATE_STOP
    private var mMediaPlayer : MediaPlayer ?=null
    private var mTimer :Timer ?=null
    private var  mTimeTask :SeekTimeTask ?=null

    //用来计算进度条并更新进度条
   inner  class SeekTimeTask : TimerTask() {
        override fun run() {
            //获取当前播放位置
            if (mMediaPlayer != null && mViewController!=null) {
                val currentPosition = mMediaPlayer!!.currentPosition
                val curPosition = (currentPosition*1.0F  / mMediaPlayer!!.duration * 100).toInt()
                mViewController!!.onSeekChange(curPosition)
            }
        }
    }

    override fun registerViewController(viewControl: IPlayerViewControl) {
        mViewController = viewControl
    }

    override fun UnRegisterViewController(mIPlayerControl: IPlayerControl) {
        mViewController = null
    }

    override fun playOrPause() {
        Log.d(TAG, "playOrPause...")
        if (mCurrentState== PLAY_STATE_STOP) {
            //如果当前状态是未开始过，则初始化并开始
            //创建播放器
            initPlayer()
            //设置数据源
            setDataToStart()
        }
        else if(mCurrentState==PLAY_STATE_PLAY){
            //如果当前的状态是播放，则暂停
            if (mMediaPlayer != null) {
                mMediaPlayer!!.pause()
                mCurrentState = PLAY_STATE_PAUSE
                stopTimer()
            }
        }
        else if (mCurrentState == PLAY_STATE_PAUSE){
            //如果当前状态是暂停的，那就继续播放
            if (mMediaPlayer != null) {
                mMediaPlayer!!.start()
                mCurrentState = PLAY_STATE_PLAY
                startTimer()
            }
        }
        //通知UI界面更新
        mViewController?.onPlayerStateChange(mCurrentState)
    }
    //停止进度条的更新
    private fun stopTimer() {
        if (mTimer != null) {
            mTimer!!.cancel()
            mTimer = null
        }
        if (mTimeTask != null) {
            mTimeTask!!.cancel()
            mTimeTask = null
        }
    }

    //设置数据
    private fun setDataToStart() {

        mMediaPlayer?.start()
        mCurrentState = PLAY_STATE_PLAY
        startTimer()
    }

    //开始进度条的更新
    private fun startTimer() {
        if (mTimer == null) {
            mTimer = Timer()
        }
        if (mTimeTask == null) {
            mTimeTask = SeekTimeTask()
        }
        //500微秒执行一次
        mTimer?.schedule(mTimeTask,0,500)
    }

    //初始化播放器
    private fun initPlayer() {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(MyApplication.context,R.raw.song)
            mMediaPlayer?.setAudioAttributes(AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build())
        }
    }

    //更新进度条
    override fun seekTo(seek: Int) {
        //进度条拉满为100
        //得到的seek是一个百分比
        if (mMediaPlayer != null) {
            val tarSeek = (seek*1.0F  / 100 * mMediaPlayer!!.duration).toInt()
            mMediaPlayer?.seekTo(tarSeek)
        }
    }

    //退出的时候要结束播放
    override fun stopPlay() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            mCurrentState = PLAY_STATE_STOP
            stopTimer()
            mMediaPlayer!!.release()
            mMediaPlayer!!.reset()
            mMediaPlayer = null
        }
    }
}