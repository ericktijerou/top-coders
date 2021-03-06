package com.ericktijerou.topcoders.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.ericktijerou.topcoders.R
import com.ericktijerou.topcoders.databinding.ActivityMainBinding
import com.ericktijerou.topcoders.ui.home.HomeFragment
import com.ericktijerou.topcoders.ui.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val sharedViewModel: SharedViewModel by viewModels()

    private val binding: ActivityMainBinding by dataBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        setDecorFitsSystemWindows(window)
        super.onCreate(savedInstanceState)
        initToolbar()
        initViewPager()
        initNavigationView()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun initViewPager() {
        with(binding) {
            viewPager.apply {
                isUserInputEnabled = false
                adapter = MainPagerAdapter(
                    this@MainActivity,
                    listOf(
                        HomeFragment(),
                        SecondFragment(),
                        SecondFragment()
                    )
                )
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        navigationView.menu.getItem(position).isChecked = true
                    }
                })
            }
        }
    }

    private fun initNavigationView() {
        with(binding) {
            navigationView.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.homeFragment -> viewPager.setCurrentItem(0, false)
                    R.id.firstFragment -> viewPager.setCurrentItem(1, false)
                    R.id.secondFragment -> viewPager.setCurrentItem(2, false)
                }
                true
            }
            navigationView.setOnNavigationItemReselectedListener {
                when (it.itemId) {
                    R.id.homeFragment -> sharedViewModel.setNavigationItem(0)
                }
            }
        }
    }

    private fun setDecorFitsSystemWindows(window: Window) {
        if (isOreo() && !isDarkThemeOn()) {
            setDecorFitsSystemWindows26(window)
        }
    }

    @Suppress("DEPRECATION")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setDecorFitsSystemWindows26(
        window: Window
    ) {
        val decorFitsFlags = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        val decorView = window.decorView
        val sysUiVis = decorView.systemUiVisibility
        window.navigationBarColor = getAttributeColor(R.attr.colorPrimary)
        decorView.systemUiVisibility = sysUiVis or decorFitsFlags
    }

    private inner class MainPagerAdapter(
        fa: FragmentActivity,
        private val items: List<Fragment>
    ) :
        FragmentStateAdapter(fa) {

        override fun getItemCount(): Int = items.size

        override fun createFragment(position: Int): Fragment = items[position]
    }
}
