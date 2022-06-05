package com.example.turingenglish.activity

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.turingenglish.BaseActivity
import com.example.turingenglish.MyApplication
import com.example.turingenglish.activity.index.IndexActivity
import com.example.turingenglish.databinding.ActivityFinishBinding
import java.util.*

class FinishActivity : BaseActivity() {
    private lateinit var etRemark : EditText
    private lateinit var btnStartNotify : Button
    private lateinit var btnBack : Button
    private lateinit var mBinding : ActivityFinishBinding

    companion object{
        private const val PERMISSION_REQUEST_CODE = 1
        val TAG = "FinishActivity"
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setContentView(mBinding.root)
        setTransparent(window,null)
        windowExplode()
        btnBack.setOnClickListener {
            onBackPressed()
        }
        btnStartNotify.setOnClickListener{
            addAlertEvent()
            Toast.makeText(MyApplication.context,"已经添加明日提醒",Toast.LENGTH_SHORT).show()
        }
        checkCalendarPermission()
    }

    override fun onBackPressed() {
        ActivityCollector.startOtherActivity(this@FinishActivity, IndexActivity::class.java)
    }

    //添加提醒事件
    private fun addAlertEvent(){
        //_id====1
        val calID: Long = 1
        val beginTime = Calendar.getInstance()
        //开始时间 *年*月（0是一月）*时*分
        beginTime.set(2022,5,7,9,0)

        val beginTimeMills = beginTime.timeInMillis
        //结束时间
        val endTime = Calendar.getInstance()
        //结束的时间
        endTime.set(2022,5,7,9,0)
        val endTimeMills = endTime.timeInMillis
        //事件内容
        val timeZone = TimeZone.getDefault().id
        val descriptions = etRemark.text.toString()
        val location = "On Your Phone"
        val title = "Tuling English Reminder"
        Log.d(TAG, "timeZone --->$timeZone")
        val eventValues = ContentValues()
        eventValues.put(CalendarContract.Events.CALENDAR_ID, calID)
        eventValues.put(CalendarContract.Events.DTSTART, beginTimeMills)
        eventValues.put(CalendarContract.Events.DTEND, endTimeMills)
        eventValues.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone)
        eventValues.put(CalendarContract.Events.TITLE, title)
        eventValues.put(CalendarContract.Events.DESCRIPTION, descriptions)
        eventValues.put(CalendarContract.Events.EVENT_LOCATION, location)
        //插入数据
        val eventUri = CalendarContract.Events.CONTENT_URI
        val contentResolver = contentResolver
        val resultUri = contentResolver.insert(eventUri, eventValues)
        Log.d(TAG, "resultUri-->$resultUri")
        val eventId = resultUri!!.lastPathSegment
        //设置提醒时间
        Log.d(TAG, "Eventid --->$eventId")
        val remainderValues = ContentValues()
        remainderValues.put(CalendarContract.Reminders.EVENT_ID, eventId)
        remainderValues.put(CalendarContract.Reminders.MINUTES, 15)
        remainderValues.put(CalendarContract.Reminders.METHOD,
            CalendarContract.Reminders.METHOD_ALERT)
        val remaindersUri = CalendarContract.Reminders.CONTENT_URI
        contentResolver.insert(remaindersUri, remainderValues)
        //打开日历
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("content://com.android.calendar/events/$eventId")
        startActivity(intent)

    }

    private fun initView() {
        mBinding = ActivityFinishBinding.inflate(layoutInflater)
        etRemark = mBinding.editFiRemark
        btnStartNotify = mBinding.btnStartNotify
        btnBack = mBinding.btnFiBack
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkCalendarPermission() {
        val readPermission = checkSelfPermission(Manifest.permission.READ_CALENDAR)
        val writePermission = checkSelfPermission(Manifest.permission.WRITE_CALENDAR)
        if (readPermission == PackageManager.PERMISSION_GRANTED && writePermission == PackageManager.PERMISSION_GRANTED) {
            //表示有权限
        } else {
            Log.d(TAG, "requestPermissions...")
            //去获取权限
            //做个提示，用户点击了确定以后再去调用请求权限
            //如果点击了不再提示，就不再去获取，如果不能使用，根据产品经理的交互去写
            requestPermissions(arrayOf(Manifest.permission.READ_CALENDAR,
                Manifest.permission.WRITE_CALENDAR),PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            //判断结果
            if (grantResults.size == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                //有权限
                Log.d(TAG, "With Permission...")
            } else {
                //没权限
                Log.d(TAG, "Without Permission")
                finish()
            }
        }
    }
}