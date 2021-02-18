package com.ericktijerou.topcoders.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ericktijerou.topcoders.ui.util.HomePageType

class SharedViewModel @ViewModelInject constructor() : ViewModel() {
    private val _navigationItem = MutableLiveData(HomePageType.NONE)
    val navigationItem: LiveData<Int> = _navigationItem

    fun setNavigationItem(navigationItem: Int) {
        _navigationItem.value = navigationItem
    }
}