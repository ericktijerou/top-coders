package com.ericktijerou.topcoders.ui.home.shared

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ericktijerou.topcoders.core.NUMBER_ZERO
import com.ericktijerou.topcoders.ui.util.HomePageType

class HomeSharedViewModel @ViewModelInject constructor() : ViewModel() {
    private val _pageReselected = MutableLiveData(NUMBER_ZERO)
    val pageReselected: LiveData<Int> = _pageReselected

    fun setPageReselected(@HomePageType pageType: Int) {
        _pageReselected.value = pageType
    }
}