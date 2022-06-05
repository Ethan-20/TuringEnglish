package com.example.turingenglish.activity

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import com.example.turingenglish.MyView
import android.os.Bundle
import android.hardware.SensorEventListener
import android.hardware.SensorEvent
import android.view.View
import com.example.turingenglish.R

/**
 * 跟随的小球的设置
 */
class ComingActivity : Activity() {
    //定义传感器的管理器
    var sensorManager: SensorManager? = null

    //定义布局内的控件
    var myView: MyView? = null

    //获取某一种传感器
    var sensor: Sensor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coming)
        myView = findViewById<View>(R.id.second_myView) as MyView
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager //实例化传感器的管理器
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    /***
     * 在onStart中注册传感器的监听事件
     */
    override fun onStart() {
        super.onStart()

        //注册监听传感状态
        sensorManager!!.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_FASTEST)
    }

    /**
     * 传感器的监听对象
     */
    var listener: SensorEventListener = object : SensorEventListener {
        //传感器改变时,一般是通过这个方法里面的参数确定传感器状态的改变
        override fun onSensorChanged(event: SensorEvent) {
            // Log.e("TAG", "-------传感状态发生变化-------");
            //获取传感器的数据
            val values = event.values
            val max = sensor!!.maximumRange //获取最大值范围
            if (values.size >= 3) {
                val x = values[0]
                val y = values[1]
                val z = values[2]
                if (Math.abs(x) > max / 8) {
                    myView!!.initdata(x.toInt(), y.toInt())
                }
                if (Math.abs(y) > max / 8) {
                    myView!!.initdata(x.toInt(), (-y).toInt())
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            //当精准度改变时
        }
    }

    /***
     * 在onDestroy中注销传感器的监听事件
     */
    override fun onDestroy() {
        super.onDestroy()
        sensorManager!!.unregisterListener(listener)
    }
}