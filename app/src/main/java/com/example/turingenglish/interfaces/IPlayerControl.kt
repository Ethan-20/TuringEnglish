package com.example.turingenglish.interfaces

/**
 * Created by Ethan On 2022/5/30 10:03
 * God bless my code!
 * 这个接口用来实现服务逻辑
 */
interface IPlayerControl {
    //播放状态：
     companion object{
         const val PLAY_STATE_PLAY = 1
         const val PLAY_STATE_PAUSE = 2
         const val PLAY_STATE_STOP = 3
     }

    //把UI的控制权接口设置给逻辑层（presenter）
    fun registerViewController(viewControl: IPlayerViewControl)

    //取消UI控制权
    fun UnRegisterViewController(mIPlayerControl: IPlayerControl)

    //播放音乐或者暂停音乐
    fun playOrPause()

    //设置播放进度
    fun seekTo(seek :Int)

    //结束播放
    fun stopPlay()
}