package com.lost.dogs.features.base

import com.lost.dogs.features.shared.base.ViewModelTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.*
import org.junit.Test
import java.lang.IllegalArgumentException

internal class BaseViewModelImplTest : ViewModelTest() {
    private val testUseCase: TestUseCase = mock()
    private val baseViewModelImpl = BaseViewModelImpl(testUseCase)

    @Test
    fun testLoading() {
        runBlocking {
            launch(Dispatchers.Main) {
                baseViewModelImpl.init()
                assert(baseViewModelImpl.isLoading().value == true)
                delay(100)
                assert(baseViewModelImpl.isLoading().value == false)
            }
        }
    }

    @Test
    fun testError() {
        runBlocking {
            launch(Dispatchers.Main) {
                whenever(testUseCase()).thenThrow(IllegalArgumentException())
                baseViewModelImpl.init()
                assert(baseViewModelImpl.error().value == null)
                delay(100)
                assert(baseViewModelImpl.error().value != null)
            }
        }
    }
}