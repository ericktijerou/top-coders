package com.ericktijerou.topcoders.ui.home.coder.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ericktijerou.topcoders.ui.entity.CoderView
import com.ericktijerou.topcoders.ui.home.coder.viewholder.CoderItemViewHolder

class CoderListAdapter(private val listener: CoderItemListener) :
    PagingDataAdapter<CoderView, RecyclerView.ViewHolder>(CODER_ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CoderItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            (holder as CoderItemViewHolder).bind(position, it, listener)
        }
    }

    companion object {
        private val CODER_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<CoderView>() {
            override fun areItemsTheSame(oldItem: CoderView, newItem: CoderView): Boolean {
                return (oldItem.username == newItem.username)
            }

            override fun areContentsTheSame(oldItem: CoderView, newItem: CoderView): Boolean =
                oldItem == newItem
        }
    }
}