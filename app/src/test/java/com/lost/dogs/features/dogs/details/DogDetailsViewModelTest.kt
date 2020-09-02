package com.lost.dogs.features.dogs.details

import com.lost.dogs.features.dogs.list.DogsListViewModel
import com.lost.dogs.features.shared.base.ViewModelTest
import com.lost.domain.models.Dog
import com.lost.domain.models.DogImage
import com.lost.domain.usecase.GetDogImagesUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class DogDetailsViewModelTest : ViewModelTest() {
    private val getDogImagesUseCase: GetDogImagesUseCase = mock()
    private val dogDetailsViewModel = DogDetailsViewModel(getDogImagesUseCase)

    @Test
    fun testSetDogImages() {
        runBlocking {
            launch(Dispatchers.Main) {
                val dog = Dog("hound")
                val dogImage = DogImage("image_url")
                val data = listOf(dogImage)
                whenever(getDogImagesUseCase(dog)).thenReturn(data)
                dogDetailsViewModel.init(dog)
                delay(100)
                assert(dogDetailsViewModel.imageUrls().value == data)
            }
        }
    }
}