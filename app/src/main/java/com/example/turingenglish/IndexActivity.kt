package com.example.turingenglish

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.turingenglish.databinding.ActivityIndexBinding
import com.example.turingenglish.ui.PracticeAdapter
import com.example.turingenglish.ui.PracticeViewModel
import com.google.android.material.navigation.NavigationView

class IndexActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityIndexBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView : NavigationView
    lateinit var recyclerView :RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparent()
        initView()
        setContentView(mBinding.root)
        setSupportActionBar(mBinding.toolbar)
        addSlideMenu()
        listenDrawerLayout()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(this,2)
        recyclerView = mBinding.recyclerView
        recyclerView.layoutManager = layoutManager
        val adapter = PracticeAdapter(this,PracticeViewModel.practices)
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

    private fun setTransparent() {
        val decorView = window.decorView
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = Color.TRANSPARENT
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

