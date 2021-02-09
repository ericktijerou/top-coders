package com.ericktijerou.topcoders.ui.home.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ericktijerou.topcoders.ui.entity.UserView
import com.ericktijerou.topcoders.ui.home.viewholder.UserItemViewHolder

class UserListAdapter(private val listener: UserItemListener) :
    PagingDataAdapter<UserView, RecyclerView.ViewHolder>(USER_ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            (holder as UserItemViewHolder).bind(it, listener)
        }
    }

    companion object {
        private val USER_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<UserView>() {
            override fun areItemsTheSame(oldItem: UserView, newItem: UserView): Boolean {
                return (oldItem.username == newItem.username)
            }

            override fun areContentsTheSame(oldItem: UserView, newItem: UserView): Boolean =
                oldItem == newItem
        }
    }
}