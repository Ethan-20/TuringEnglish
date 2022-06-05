package com.example.turingenglish

import android.content.Context
import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.transition.Explode
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.turingenglish.receiver.NetWorkStateReceiver

/**
 * Created by Ethan On 2022/5/30 9:03
 * God bless my code!
 */
open class BaseActivity: AppCompatActivity() {

    private  var netStateReceiver:NetWorkStateReceiver ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onDestroy() {
        super.onDestroy()

    }
    open fun windowExplode() {
        window.enterTransition = Explode().setDuration(300)
        window.exitTransition = Explode().setDuration(300)
        window.reenterTransition = Explode().setDuration(300)
        window.returnTransition = Explode().setDuration(300)
    }
    fun setTransparent(window: Window, view: androidx.appcompat.widget.Toolbar?) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.statusBarColor = Color.TRANSPARENT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT&&view!=null) {

            val params = view.layoutParams

            val topMargin =getStatusBarHeight(view.context);

            params.height = params.height + topMargin;

            view.setPadding(
                view.paddingLeft,
                view.paddingTop + topMargin,
                view.paddingRight,
                view.paddingBottom
            );
            view.layoutParams = params;
        }

    }
    private var mStatusBarHeight = 0
    private fun  getStatusBarHeight(context: Context) :Int{

        if (mStatusBarHeight > 0) {
            return mStatusBarHeight;
        }
        var statusBarHeight = 0;
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.resources.getDimensionPixelSize(resourceId);
        }
        mStatusBarHeight = statusBarHeight;
        return mStatusBarHeight;
    }
    fun registerReceiver() {
        if (netStateReceiver == null) {
            netStateReceiver = NetWorkStateReceiver()
        }
        netStateReceiver = NetWorkStateReceiver()
        val filter =  IntentFilter()
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(netStateReceiver, filter)
    }
     fun unregisterReceiver(){
        unregisterReceiver(netStateReceiver)
    }


}