package com.ericktijerou.topcoders.ui

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.ericktijerou.topcoders.R
import com.ericktijerou.topcoders.databinding.ActivityMainBinding
import com.ericktijerou.topcoders.ui.home.HomeFragment
import com.ericktijerou.topcoders.ui.util.dataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by dataBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        setupStatusBar()
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        initViewPager()
        initNavigationView()
    }

    private fun setupStatusBar() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            window.statusBarColor = Color.BLACK
        }
    }

    private fun initViewPager() {
        with(binding) {
            viewPager.apply {
                isUserInputEnabled = false
                adapter = MainPagerAdapter(this@MainActivity, listOf(HomeFragment(), FirstFragment(), SecondFragment()))
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

    private inner class MainPagerAdapter(fa: FragmentActivity, private val items: List<Fragment>) :
        FragmentStateAdapter(fa) {

        override fun getItemCount(): Int = items.size

        override fun createFragment(position: Int): Fragment = items[position]
    }
}
