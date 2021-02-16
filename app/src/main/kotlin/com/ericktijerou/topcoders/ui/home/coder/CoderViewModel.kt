package com.ericktijerou.topcoders.ui.home.coder

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.ericktijerou.topcoders.domain.usecase.GetUserListUseCase
import com.ericktijerou.topcoders.ui.entity.CoderView
import com.ericktijerou.topcoders.ui.mapper.toView
import kotlinx.coroutines.flow.map

class CoderViewModel @ViewModelInject constructor(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {

    val coderList: LiveData<PagingData<CoderView>> by lazy {
        getUserListUseCase.invoke("followers:>100 sort:followers-desc").map { pagingData ->
            pagingData.map { it.toView() }
        }.cachedIn(viewModelScope).asLiveData()
    }
}