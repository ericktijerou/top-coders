package com.ericktijerou.topcoders.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.ericktijerou.topcoders.R
import com.ericktijerou.topcoders.databinding.FragmentHomeBinding
import com.ericktijerou.topcoders.ui.SharedViewModel
import com.ericktijerou.topcoders.ui.home.coder.CoderHomeFragment
import com.ericktijerou.topcoders.ui.home.repo.RepoHomeFragment
import com.ericktijerou.topcoders.ui.home.shared.HomeSharedViewModel
import com.ericktijerou.topcoders.ui.util.*
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val homeSharedViewModel: HomeSharedViewModel by activityViewModels()

    private val binding: FragmentHomeBinding by dataBinding()

    private val items by lazy {
        listOf<Pair<String, Fragment>>(
            getString(R.string.label_coder) to CoderHomeFragment(),
            getString(R.string.label_repo) to RepoHomeFragment()
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewPager()
        obsNavigationItem()
    }

    private fun initViewPager() {
        with(binding) {
            homePager.offscreenPageLimit = 2
            homePager.adapter = HomeAdapter(this@HomeFragment, items)
            tabLayout.setupWithViewPager2(homePager)
            val mainAppbar = requireActivity().findViewById<AppBarLayout>(R.id.mainAppBar)
            homeAppBar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
                override fun onStateChanged(appBarLayout: AppBarLayout, state: Int) {
                    when (state) {
                        AppBarState.COLLAPSED -> {
                            homeAppBar.elevation = 0f
                            mainAppbar.elevation = 4.dpToPixels(requireContext())
                        }
                        else -> {
                            homeAppBar.elevation = 4.dpToPixels(requireContext())
                            mainAppbar.elevation = 0f
                        }
                    }
                }
            })
            homePager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    homeAppBar.setExpanded(true)
                }
            })
        }
    }

    private fun obsNavigationItem() {
        sharedViewModel.navigationItem.observe(viewLifecycleOwner) {
            binding.homeAppBar.setExpanded(true, false)
            val pageType = when (items[binding.homePager.currentItem].second) {
                is CoderHomeFragment -> HomePageType.HOME_CODER
                is RepoHomeFragment -> HomePageType.HOME_REPO
                else -> HomePageType.NONE
            }
            homeSharedViewModel.setPageReselected(pageType)
        }
    }

    inner class HomeAdapter(fragment: Fragment, private val items: List<Pair<String, Fragment>>) :
        FragmentStateAdapter(fragment), PageTitleProvider {
        override fun getItemCount(): Int = items.size
        override fun createFragment(position: Int): Fragment = items[position].second
        override fun getPageTitle(position: Int): String = items[position].first
    }
}