package com.example.turingenglish.service

import android.app.Service
import android.content.Intent
import com.example.turingenglish.presenter.PlayerPresenter

class PlayerService : Service() {

    private  var mPlayerPresenter :PlayerPresenter ? = null

    override fun onCreate() {
        super.onCreate()
        if (mPlayerPresenter==null) {
            mPlayerPresenter = PlayerPresenter()
        }
    }
    override fun onBind(intent: Intent): PlayerPresenter? {
        return mPlayerPresenter
    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayerPresenter = null
    }
}