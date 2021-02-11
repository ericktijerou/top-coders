package com.ericktijerou.topcoders.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ericktijerou.topcoders.R
import com.ericktijerou.topcoders.databinding.FragmentHomeBinding
import com.ericktijerou.topcoders.ui.home.coder.CoderHomeFragment
import com.ericktijerou.topcoders.ui.home.repo.RepoHomeFragment
import com.ericktijerou.topcoders.ui.util.PageTitleProvider
import com.ericktijerou.topcoders.ui.util.dataBinding
import com.ericktijerou.topcoders.ui.util.setupWithViewPager2

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by dataBinding()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        with(binding) {
            val items = listOf(
                getString(R.string.label_coder) to CoderHomeFragment(),
                getString(R.string.label_repo) to RepoHomeFragment()
            )
            homePager.adapter = HomeAdapter(this@HomeFragment, items)
            tabLayout.setupWithViewPager2(homePager)
        }
    }

    inner class HomeAdapter(fragment: Fragment, private val items: List<Pair<String, Fragment>>) :
        FragmentStateAdapter(fragment), PageTitleProvider {
        override fun getItemCount(): Int = items.size
        override fun createFragment(position: Int): Fragment = items[position].second
        override fun getPageTitle(position: Int): String? = items[position].first
    }

}