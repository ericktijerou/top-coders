package com.ericktijerou.topcoders.ui.home.repo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.ericktijerou.topcoders.domain.usecase.GetRepoListUseCase
import com.ericktijerou.topcoders.ui.entity.RepoView
import com.ericktijerou.topcoders.ui.mapper.toView
import kotlinx.coroutines.flow.map

class RepoViewModel @ViewModelInject constructor(
    private val getRepoListUseCase: GetRepoListUseCase
) : ViewModel() {

    val repoList: LiveData<PagingData<RepoView>> by lazy {
        getRepoListUseCase.invoke("stars:>100 sort:stars-desc").map { pagingData ->
            pagingData.map { it.toView() }
        }.cachedIn(viewModelScope).asLiveData()
    }
}