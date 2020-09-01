package com.lost.dogs.features.dogs.list

import android.os.Debug
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lost.dogs.features.shared.base.BaseNetworkViewModel
import com.lost.domain.models.Dog
import com.lost.domain.usecase.GetDogsUseCase
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScoped
class DogsListViewModel @Inject constructor(private val getDogsUseCase: GetDogsUseCase): BaseNetworkViewModel() {
    private val dogsList = MutableLiveData<List<Dog>>()
    fun dogsList(): LiveData<List<Dog>> = dogsList

    fun init() {
        viewModelScope.launch(exceptionHandler) {
            dogsList.value = getDogsUseCase()
            Log.d("LUKELUKE", dogsList.value.toString())
        }.addToLoadingState()
    }
}