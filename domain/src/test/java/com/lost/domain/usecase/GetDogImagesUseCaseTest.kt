package com.lost.domain.usecase;

import com.lost.domain.models.Dog
import com.lost.domain.repository.DogRepository;
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking

import org.junit.After;
import org.junit.Test

class GetDogImagesUseCaseTest {
    private val repository: DogRepository = mock()
    private val useCase = GetDogImagesUseCase(repository)

    @After
    fun tearDown() {
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun testShouldCallGet() {
        runBlocking {
            val dog = Dog("test", "dog")
            useCase.invoke(dog)
            verify(repository).get(dog)
        }
    }
}