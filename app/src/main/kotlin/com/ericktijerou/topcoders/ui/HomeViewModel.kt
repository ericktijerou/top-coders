package com.ericktijerou.topcoders.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.ericktijerou.topcoders.domain.entity.toView
import com.ericktijerou.topcoders.domain.usecase.GetUserListUseCase
import com.ericktijerou.topcoders.ui.entity.UserView
import kotlinx.coroutines.flow.map

class HomeViewModel @ViewModelInject constructor(
    private val getUserListUseCase: GetUserListUseCase
): ViewModel() {

    val userList: LiveData<PagingData<UserView>> by lazy {
        getUserListUseCase.invoke("peru").map { pagingData ->
            pagingData.map { it.toView() }
        }.cachedIn(viewModelScope).asLiveData()
    }
}