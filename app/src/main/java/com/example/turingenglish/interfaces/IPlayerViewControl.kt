package com.example.turingenglish.interfaces

/**
 * Created by Ethan On 2022/5/30 10:07
 * God bless my code!
 * 这个接口用来更新界面
 */
interface IPlayerViewControl {
    //播放状态改变通知
    fun onPlayerStateChange(state:Int)

    //播放进度改变
    fun onSeekChange(seek :Int)

}