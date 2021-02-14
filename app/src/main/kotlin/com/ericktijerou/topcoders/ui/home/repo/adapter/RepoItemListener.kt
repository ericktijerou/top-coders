package com.ericktijerou.topcoders.ui.home.repo.adapter

import com.ericktijerou.topcoders.ui.entity.RepoView

interface RepoItemListener {
    fun onRepoClick(item: RepoView)
}