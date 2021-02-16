package com.ericktijerou.topcoders.ui.home.coder.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ericktijerou.topcoders.R
import com.ericktijerou.topcoders.databinding.ItemCoderBinding
import com.ericktijerou.topcoders.ui.entity.CoderView
import com.ericktijerou.topcoders.ui.home.coder.adapter.CoderItemListener

class CoderItemViewHolder(val binding: ItemCoderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(position: Int, coder: CoderView, listener: CoderItemListener?) {
        with(binding) {
            this.model = coder
            this.listener = listener
            this.position = "#${position + 1}"
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup): CoderItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_coder, parent, false)
            val binding = ItemCoderBinding.bind(view)
            return CoderItemViewHolder(binding)
        }
    }
}