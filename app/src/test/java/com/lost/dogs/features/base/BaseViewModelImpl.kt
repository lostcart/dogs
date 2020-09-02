package com.lost.dogs.features.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lost.dogs.features.shared.base.BaseNetworkViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class BaseViewModelImpl @Inject constructor(val testUseCase: TestUseCase) : BaseNetworkViewModel() {
    private val testValue = MutableLiveData<String>()

    fun testValue(): LiveData<String> = testValue

    fun init() {
        viewModelScope.launch(exceptionHandler) {
            testValue.value = testUseCase()
        }.addToLoadingState()
    }
}