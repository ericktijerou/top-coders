package com.ericktijerou.topcoders.ui.home.coder.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.ericktijerou.topcoders.ui.home.coder.viewholder.LoadStateViewHolder

class CoderLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder.create(parent, retry)
    }
}