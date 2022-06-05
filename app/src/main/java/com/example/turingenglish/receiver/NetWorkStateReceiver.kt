package com.example.turingenglish.receiver

import android.app.usage.NetworkStats
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.util.Log
import android.widget.Toast


/**
 * Created by Ethan On 2022/6/4 20:37
 * God bless my code!
 */
class NetWorkStateReceiver : BroadcastReceiver (){
    val TAG = "NetWorkStateReceiver"

    override fun onReceive(context: Context?, intent: Intent?) {
        var tempStr :String
        Log.d(TAG,"网络状态发生变化")
        //检测API是不是小于23，因为到了API23之后getNetworkInfo(int networkType)方法被弃用
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            //获得ConnectivityManager对象
            val connMgr =
                context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            //获取ConnectivityManager对象对应的NetworkInfo对象
            //获取WIFI连接的信息
            val wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            //获取移动数据连接的信息
            val dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            tempStr = if (wifiNetworkInfo!!.isConnected && dataNetworkInfo!!.isConnected) {
                Toast.makeText(context, "WIFI已连接,移动数据已连接", Toast.LENGTH_SHORT).show()
                "WIFI已连接,移动数据已连接"
            } else if (wifiNetworkInfo!!.isConnected && !dataNetworkInfo!!.isConnected) {
                Toast.makeText(context, "WIFI已连接,移动数据已断开", Toast.LENGTH_SHORT).show()
                "WIFI已连接,移动数据已断开"
            } else if (!wifiNetworkInfo!!.isConnected && dataNetworkInfo!!.isConnected) {
                Toast.makeText(context, "WIFI已断开,移动数据已连接", Toast.LENGTH_SHORT).show()
                "WIFI已断开,移动数据已连接"
            } else {
                Toast.makeText(context, "WIFI已断开,移动数据已断开", Toast.LENGTH_SHORT).show()
                "WIFI已断开,移动数据已断开"
            }
//API大于23时使用下面的方式进行网络监听
        } else {
            //获得ConnectivityManager对象
            val connMgr =
                context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            //获取所有网络连接的信息
            val networks = connMgr.allNetworks
            //用于存放网络连接信息
            val sb = StringBuilder()
            //通过循环将网络信息逐个取出来
            for (i in networks.indices) {
                //获取ConnectivityManager对象对应的NetworkInfo对象
                val networkInfo = connMgr.getNetworkInfo(networks[i])
                sb.append(networkInfo!!.typeName)
            }
            tempStr = sb.toString()
            if (tempStr == "") {
                Toast.makeText(context, "网络已断开", Toast.LENGTH_SHORT).show()
            }
            else if(tempStr == "MOBILE"){
                Toast.makeText(context, "现在连接的网络是移动数据，请注意流量的使用", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, "正在使用WIFI", Toast.LENGTH_SHORT).show()
            }
        }
    }


}