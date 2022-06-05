package com.example.turingenglish.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.app.ActivityOptionsCompat
import com.example.turingenglish.MainActivity
import com.example.turingenglish.MyApplication

/**
 * Created by Ethan On 2022/6/4 19:09
 * God bless my code!
 */
class ActivityCollector {
    companion object{
        var activities: ArrayList<Activity> = ArrayList()

        fun addActivity(activity: Activity?) {
            if (activity != null) {
                activities.add(activity)
            }
        }

        fun removeActivity(activity: Activity?) {
            activities.remove(activity)
        }

        fun finishAll() {
            MainActivity.needRefresh = true
            for (activity in activities) {
                if (!activity.isFinishing) {
                    activity.finish()
                }
            }
        }

        // 启动新的Activity
        /*
     * 注意：
     * Context中有一个startActivity方法，Activity继承自Context，重载了startActivity方法
     * 如果使用Activity的startActivity方法，不会有任何限制
     * 而如果使用Context的startActivity方法的話，就需要开启一个新的的task
     * 遇到这个异常，是因为使用了Context的startActivity方法。解决办法是，加一个flag
     *
     * */
        fun startOtherActivity(context: Context, cls: Class<*>?) {
            val intent = Intent()
            intent.setClass(MyApplication.context, cls!!)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startOtherActivity(
            context: Context,
            cls: Class<*>?,
            activityOptionsCompat: ActivityOptionsCompat,
        ) {
            val intent = Intent()
            intent.setClass(MyApplication.context, cls!!)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent, activityOptionsCompat.toBundle())
        }
    }
}