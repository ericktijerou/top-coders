package com.ericktijerou.topcoders.ui.home.repo.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ericktijerou.topcoders.R
import com.ericktijerou.topcoders.databinding.ItemRepoBinding
import com.ericktijerou.topcoders.ui.entity.RepoView
import com.ericktijerou.topcoders.ui.home.repo.adapter.RepoItemListener

class RepoItemViewHolder(val binding: ItemRepoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(repo: RepoView, listener: RepoItemListener?) {
        with(binding) {
            this.model = repo
            this.listener = listener
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup): RepoItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repo, parent, false)
            val binding = ItemRepoBinding.bind(view)
            return RepoItemViewHolder(binding)
        }
    }
}