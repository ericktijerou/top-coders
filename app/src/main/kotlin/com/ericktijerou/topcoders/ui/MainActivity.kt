package com.ericktijerou.topcoders.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.ericktijerou.topcoders.R
import com.ericktijerou.topcoders.databinding.ActivityMainBinding
import com.ericktijerou.topcoders.ui.home.HomeFragment
import com.ericktijerou.topcoders.ui.util.dataBinding
import com.ericktijerou.topcoders.ui.util.getAttributeColor
import com.ericktijerou.topcoders.ui.util.isDarkThemeOn
import com.ericktijerou.topcoders.ui.util.isOreo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by dataBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        setDecorFitsSystemWindows(window)
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        initViewPager()
        initNavigationView()
    }


    private fun initViewPager() {
        with(binding) {
            viewPager.apply {
                isUserInputEnabled = false
                adapter = MainPagerAdapter(
                    this@MainActivity,
                    listOf(HomeFragment(), FirstFragment(), SecondFragment())
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
        window.navigationBarColor = getAttributeColor(R.attr.colorOnPrimary)
        decorView.systemUiVisibility = sysUiVis or decorFitsFlags
    }

    private inner class MainPagerAdapter(fa: FragmentActivity, private val items: List<Fragment>) :
        FragmentStateAdapter(fa) {

        override fun getItemCount(): Int = items.size

        override fun createFragment(position: Int): Fragment = items[position]
    }
}
