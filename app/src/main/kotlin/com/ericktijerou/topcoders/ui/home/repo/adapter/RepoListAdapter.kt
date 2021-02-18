package com.ericktijerou.topcoders.ui.home.repo.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ericktijerou.topcoders.ui.entity.RepoView
import com.ericktijerou.topcoders.ui.home.repo.viewholder.RepoItemViewHolder

class RepoListAdapter(private val listener: RepoItemListener) :
    PagingDataAdapter<RepoView, RecyclerView.ViewHolder>(REPO_ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RepoItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            (holder as RepoItemViewHolder).bind(position, it, listener)
        }
    }

    companion object {
        private val REPO_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<RepoView>() {
            override fun areItemsTheSame(oldItem: RepoView, newItem: RepoView): Boolean {
                return (oldItem.id == newItem.id)
            }

            override fun areContentsTheSame(oldItem: RepoView, newItem: RepoView): Boolean =
                oldItem == newItem
        }
    }
}