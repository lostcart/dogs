package com.lost.dogs.features.dogs.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lost.dogs.features.shared.base.BaseNetworkViewModel
import com.lost.domain.models.Dog
import com.lost.domain.models.DogImage
import com.lost.domain.usecase.GetDogImagesUseCase
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScoped
class DogDetailsViewModel @Inject constructor(private val getDogImagesUseCase: GetDogImagesUseCase) : BaseNetworkViewModel() {
    private val imageUrls = MutableLiveData<List<DogImage>>()
    fun imageUrls(): LiveData<List<DogImage>> = imageUrls

    fun init(dog: Dog) {
        viewModelScope.launch(exceptionHandler) {
            imageUrls.value = getDogImagesUseCase(dog)
        }.addToLoadingState()
    }
}