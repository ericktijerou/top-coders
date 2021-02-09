package com.ericktijerou.topcoders.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ericktijerou.topcoders.R
import com.ericktijerou.topcoders.databinding.FragmentHomeBinding
import com.ericktijerou.topcoders.ui.entity.UserView
import com.ericktijerou.topcoders.ui.home.adapter.UserItemListener
import com.ericktijerou.topcoders.ui.home.adapter.UserListAdapter
import com.ericktijerou.topcoders.ui.home.adapter.UserLoadStateAdapter
import com.ericktijerou.topcoders.ui.util.dataBinding
import com.ericktijerou.topcoders.ui.util.showErrorMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), UserItemListener,
    SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: HomeViewModel by viewModels()
    private val binding: FragmentHomeBinding by dataBinding()
    private val adapter: UserListAdapter = UserListAdapter(this)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
        obsUsers()
    }

    override fun onPause() {
        adapter.removeLoadStateListener {
            Log.e("MainFragmet", "LoadStateListener Removed")
        }
        super.onPause()
    }

    private fun initUI() {
        with(binding) {
            srlRefresh.setOnRefreshListener(this@HomeFragment)
            initAdapterLoadingState()
            rvUsers.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            rvUsers.adapter =
                adapter.withLoadStateFooter(footer = UserLoadStateAdapter { adapter.retry() })
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
        viewModel.userList.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onUserClick(item: UserView) {
        // EMPTY
    }

    override fun onRefresh() {
        adapter.refresh()
    }

    override fun onDestroyView() {
        if (binding.rvUsers.adapter != null)
            binding.rvUsers.adapter = null
        super.onDestroyView()
    }
}
