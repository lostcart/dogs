package com.lost.dogs.features.shared.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule

internal open class ViewModelTest{
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()
}