package com.ericktijerou.topcoders.ui.home.coder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ericktijerou.topcoders.R
import com.ericktijerou.topcoders.ui.entity.CoderView
import com.ericktijerou.topcoders.ui.home.coder.adapter.CoderItemListener
import com.ericktijerou.topcoders.ui.home.coder.adapter.CoderListAdapter
import com.ericktijerou.topcoders.ui.home.coder.adapter.CoderLoadStateAdapter
import com.ericktijerou.topcoders.ui.home.shared.PageHomeFragment
import com.ericktijerou.topcoders.ui.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoderHomeFragment : PageHomeFragment(), CoderItemListener,
    SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: CoderViewModel by viewModels()
    private val adapter: CoderListAdapter = CoderListAdapter(this)
    override val pageType: Int get() = HomePageType.HOME_CODER

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
        obsUsers()
    }

    override fun onPause() {
        adapter.removeLoadStateListener {
            Log.e("DevHomeFragment", "LoadStateListener Removed")
        }
        super.onPause()
    }

    private fun initUI() {
        with(binding) {
            srlRefresh.setOnRefreshListener(this@CoderHomeFragment)
            initAdapterLoadingState()
            recycler.layoutManager = ScrollingLinearLayoutManager(requireContext())
            recycler.addItemDecoration(
                MarginItemDecoration(R.dimen.spacing_small)
            )
            recycler.adapter =
                adapter.withLoadStateFooter(footer = CoderLoadStateAdapter { adapter.retry() })
            executePendingBindings()
        }
    }

    private fun initAdapterLoadingState() {
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                binding.srlRefresh.isRefreshing = true
            } else {
                binding.srlRefresh.isRefreshing = false

                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let { requireContext().showErrorMessage(it.error.message ?: "Error") }
            }
        }
    }

    private fun obsUsers() {
        viewModel.coderList.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onUserClick(item: CoderView) {
        // EMPTY
    }

    override fun onRefresh() {
        adapter.refresh()
    }

    override fun onDestroyView() {
        if (binding.recycler.adapter != null)
            binding.recycler.adapter = null
        super.onDestroyView()
    }
}
