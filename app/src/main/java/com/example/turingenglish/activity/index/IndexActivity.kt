package com.example.turingenglish.activity.index

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.turingenglish.BaseActivity
import com.example.turingenglish.Constants
import com.example.turingenglish.MyApplication
import com.example.turingenglish.R
import com.example.turingenglish.activity.MusicActivity
import com.example.turingenglish.databinding.ActivityIndexBinding
import com.example.turingenglish.listener.OnItemClickListener
import com.example.turingenglish.ui.adapters.PracticeAdapter
import com.example.turingenglish.ui.PracticeViewModel
import com.google.android.material.navigation.NavigationView


class IndexActivity : BaseActivity() {
    private lateinit var mBinding: ActivityIndexBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView : NavigationView
    lateinit var recyclerView :RecyclerView
     val TAG = "IndexActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setContentView(mBinding.root)
        setSupportActionBar(mBinding.toolbar)
        setTransparent(window, mBinding.toolbar)
        addSlideMenu()
        listenDrawerLayout()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(this,2)
        recyclerView = mBinding.recyclerView
        recyclerView.layoutManager = layoutManager
        val adapter = PracticeAdapter(PracticeViewModel.practices)
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(Id:Int) {
                when (Id) {
                    Constants.reciteWords -> {
                        //TODO 实现背单词
                        val intent = Intent(MyApplication.context, VocabularyActivity::class.java)
                        startActivity(intent)

                    }

                    Constants.Reading -> {
                        //TODO 实现阅读
                        val intent = Intent(MyApplication.context, MusicActivity::class.java)
                        startActivity(intent)
                    }

                    Constants.followingSentences -> {
                        //TODO 实现句子跟读
                    }

                    Constants.translating -> {
                        //TODO 实现翻译
                    }

                    Constants.talkToBot ->{
                        //TODO 实现AI对话
                    }

                    Constants.comingSoon -> {
                        //TODO 敬请期待新功能
                    }
                }
            }
        })
        recyclerView.adapter = adapter

    }


    private fun addSlideMenu() {
        //给任务栏添加一个滑动菜单按钮
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.te_menu)
        }
        navView.setCheckedItem(R.id.navCall)
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }
    }




    private fun initView() {
        mBinding = ActivityIndexBinding.inflate(layoutInflater)
        drawerLayout = mBinding.drawerLayout
        navView = mBinding.navView
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)

            R.id.search -> Toast.makeText(this, "You clicked search",
                Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "You clicked Settings",
                Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun listenDrawerLayout(){
        drawerLayout.addDrawerListener(object :DrawerLayout.DrawerListener{
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

            }

            override fun onDrawerOpened(drawerView: View) {

            }
            //关闭的时候记得要关掉键盘输入框
            override fun onDrawerClosed(drawerView: View) {
                val manager = getSystemService(Context.INPUT_METHOD_SERVICE)
                        as InputMethodManager
                manager.hideSoftInputFromWindow(
                    drawerView.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }

            override fun onDrawerStateChanged(newState: Int) {


            }

        })
    }
}

