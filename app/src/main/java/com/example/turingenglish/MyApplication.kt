package com.example.turingenglish

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Toolbar
import androidx.core.graphics.ColorUtils

/**
 * Created by Ethan On 2022/5/13 18:50
 * God bless my code!
 */
class MyApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

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
        private fun  getStatusBarHeight(context:Context) :Int{

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

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}