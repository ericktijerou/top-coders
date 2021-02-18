package com.ericktijerou.topcoders.ui.home.shared

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ericktijerou.topcoders.R
import com.ericktijerou.topcoders.databinding.FragmentPageHomeBinding
import com.ericktijerou.topcoders.ui.util.HomePageType
import com.ericktijerou.topcoders.ui.util.dataBinding

abstract class PageHomeFragment : Fragment(R.layout.fragment_page_home) {

    private val homeSharedViewModel: HomeSharedViewModel by activityViewModels()
    protected val binding: FragmentPageHomeBinding by dataBinding()

    @HomePageType
    abstract val pageType: Int

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        obsPageReselected()
    }

    private fun obsPageReselected() {
        homeSharedViewModel.pageReselected.observe(viewLifecycleOwner) {
            if (pageType == it) binding.recycler.smoothScrollToPosition(0)
        }
    }
}