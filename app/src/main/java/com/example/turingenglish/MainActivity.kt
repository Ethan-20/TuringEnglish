package com.example.turingenglish

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : BaseActivity() {
    companion object{
        var needRefresh = true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTransparent(window,null)
    }
}