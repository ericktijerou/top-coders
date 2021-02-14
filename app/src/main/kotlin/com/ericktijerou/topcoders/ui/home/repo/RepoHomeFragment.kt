package com.ericktijerou.topcoders.ui.home.repo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ericktijerou.topcoders.R
import com.ericktijerou.topcoders.databinding.FragmentRepoHomeBinding
import com.ericktijerou.topcoders.ui.entity.RepoView
import com.ericktijerou.topcoders.ui.home.coder.adapter.CoderLoadStateAdapter
import com.ericktijerou.topcoders.ui.home.repo.adapter.RepoItemListener
import com.ericktijerou.topcoders.ui.home.repo.adapter.RepoListAdapter
import com.ericktijerou.topcoders.ui.util.dataBinding
import com.ericktijerou.topcoders.ui.util.showErrorMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoHomeFragment : Fragment(R.layout.fragment_repo_home), RepoItemListener,
    SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: RepoViewModel by viewModels()
    private val binding: FragmentRepoHomeBinding by dataBinding()
    private val adapter: RepoListAdapter = RepoListAdapter(this)

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
            rvRepos.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            rvRepos.adapter =
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
        if (binding.rvRepos.adapter != null)
            binding.rvRepos.adapter = null
        super.onDestroyView()
    }
}
