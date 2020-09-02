package com.lost.dogs.features.dogs.list

import com.lost.dogs.features.shared.base.ViewModelTest
import com.lost.domain.models.Dog
import com.lost.domain.usecase.GetDogsUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test


internal class TagsListViewModelTest : ViewModelTest() {
    private val getDogsUseCase: GetDogsUseCase = mock()
    private val dogsListViewModel = DogsListViewModel(getDogsUseCase)

    @Test
    fun testSetDogList() {
        runBlocking {
            launch(Dispatchers.Main) {
                val data = listOf(Dog("hound"))
                whenever(getDogsUseCase()).thenReturn(data)
                dogsListViewModel.init()
                delay(100)
                assert(dogsListViewModel.dogsList().value == data)
            }
        }
    }
}