package com.example.turingenglish

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.turingenglish.databinding.ActivityVocabularyBinding

class VocabularyActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityVocabularyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityVocabularyBinding.inflate(layoutInflater)
        setSupportActionBar(mBinding.toolbar)
        setContentView(mBinding.root)
        MyApplication.setTransparent(window,mBinding.toolbar)
        addReturnButton()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.vocabulary_menu, menu)
        return true
    }

    private fun addReturnButton(){
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }


}
