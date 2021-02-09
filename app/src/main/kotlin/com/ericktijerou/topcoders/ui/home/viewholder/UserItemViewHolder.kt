package com.ericktijerou.topcoders.ui.home.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ericktijerou.topcoders.R
import com.ericktijerou.topcoders.databinding.ItemUserBinding
import com.ericktijerou.topcoders.ui.entity.UserView
import com.ericktijerou.topcoders.ui.home.adapter.UserItemListener

class UserItemViewHolder(val binding: ItemUserBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(user: UserView, listener: UserItemListener?) {
        with(binding) {
            this.model = user
            this.listener = listener
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup): UserItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user, parent, false)
            val binding = ItemUserBinding.bind(view)
            return UserItemViewHolder(binding)
        }
    }
}