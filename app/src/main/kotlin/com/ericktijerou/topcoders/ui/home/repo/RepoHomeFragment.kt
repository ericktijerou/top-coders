package com.ericktijerou.topcoders.ui.home.repo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ericktijerou.topcoders.R
import com.ericktijerou.topcoders.ui.entity.RepoView
import com.ericktijerou.topcoders.ui.home.coder.adapter.CoderLoadStateAdapter
import com.ericktijerou.topcoders.ui.home.repo.adapter.RepoItemListener
import com.ericktijerou.topcoders.ui.home.repo.adapter.RepoListAdapter
import com.ericktijerou.topcoders.ui.home.shared.PageHomeFragment
import com.ericktijerou.topcoders.ui.util.HomePageType
import com.ericktijerou.topcoders.ui.util.MarginItemDecoration
import com.ericktijerou.topcoders.ui.util.ScrollingLinearLayoutManager
import com.ericktijerou.topcoders.ui.util.showErrorMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoHomeFragment : PageHomeFragment(), RepoItemListener,
    SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: RepoViewModel by viewModels()
    private val adapter: RepoListAdapter = RepoListAdapter(this)
    override val pageType: Int get() = HomePageType.HOME_REPO

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
        obsRepos()
    }

    override fun onPause() {
        adapter.removeLoadStateListener {
            Log.e("RepoHomeFragment", "LoadStateListener Removed")
        }
        super.onPause()
    }

    private fun initUI() {
        with(binding) {
            srlRefresh.setOnRefreshListener(this@RepoHomeFragment)
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

    private fun obsRepos() {
        viewModel.repoList.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onRepoClick(item: RepoView) {
        // Empty
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
