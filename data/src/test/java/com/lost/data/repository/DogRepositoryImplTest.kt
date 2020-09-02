package com.lost.data.repository

import com.lost.data.datastore.DogCacheDataStore
import com.lost.data.datastore.DogRemoteDataStore
import com.lost.data.mapper.DogImagesMapper
import com.lost.data.mapper.DogsMapper
import com.lost.data.models.DogImagesResponse
import com.lost.data.models.DogsResponse
import com.lost.domain.models.Dog
import com.lost.domain.models.DogImage
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.lang.Exception

class DogRepositoryImplTest {
    private val remoteDataStore: DogRemoteDataStore = mock()
    private val cacheDataStore: DogCacheDataStore = mock()
    private val dogsMapper = DogsMapper()
    private val dogImagesMapper = DogImagesMapper()
    private val dogRepositoryImpl =
        DogRepositoryImpl(remoteDataStore, cacheDataStore, dogsMapper, dogImagesMapper)

    // GET DOGS
    @Test
    fun testGetRemoteDogs() {
        runBlocking {
            val dogsResponse = provideDogsResponse()
            whenever(remoteDataStore.get()).thenReturn(dogsResponse)

            val returnValue = dogRepositoryImpl.get()
            val dogOne = Dog(dogBreedOne)
            val dogTwo = Dog(dogBreedTwo)
            val dogThree = Dog(dogBreedTwo, dogSubbreed)
            assert(returnValue == listOf(dogOne, dogTwo, dogThree))
        }
    }

    @Test
    fun testInsertDogsToCache() {
        runBlocking {
            val dogsResponse = provideDogsResponse()
            whenever(remoteDataStore.get()).thenReturn(dogsResponse)
            dogRepositoryImpl.get()
            verify(cacheDataStore).insert(dogsResponse)
        }
    }

    @Test
    fun testGetCachedDogs() {
        runBlocking {
            val dogsResponse = provideDogsResponse()
            whenever(cacheDataStore.get()).thenReturn(dogsResponse)
            val returnValue = dogRepositoryImpl.get()
            val dogOne = Dog(dogBreedOne)
            val dogTwo = Dog(dogBreedTwo)
            val dogThree = Dog(dogBreedTwo, dogSubbreed)
            assert(returnValue == listOf(dogOne, dogTwo, dogThree))
        }
    }

    @Test
    fun testGetDogsThrowsOnFailure() {
        runBlocking {
            val exception = IllegalArgumentException()
            whenever(remoteDataStore.get()).thenThrow(exception)

            var result: Exception? = null
            try {
                dogRepositoryImpl.get()
            } catch (exception: IllegalArgumentException) {
                result = exception
            }
            assert(result == exception)
        }
    }

    // GET DOG IMAGES
    @Test
    fun testGetRemoteDogImages() {
        runBlocking {
            val dogImagesResponse = provideDogImagesResponse()
            whenever(remoteDataStore.get(dogBreedOne)).thenReturn(dogImagesResponse)

            val returnValue = dogRepositoryImpl.get(Dog(dogBreedOne))
            val dogImage = DogImage(dogImageUrl)
            verify(remoteDataStore).get(dogBreedOne)
            assert(returnValue == listOf(dogImage))
        }
    }

    @Test
    fun testInsertDogImagesToCache() {
        runBlocking {
            val dogImagesResponse = provideDogImagesResponse()
            val dog = Dog(dogBreedOne)
            whenever(remoteDataStore.get(dog.breed)).thenReturn(dogImagesResponse)
            dogRepositoryImpl.get(dog)
            verify(cacheDataStore).insert(dog.breed, dogImagesResponse)
        }
    }

    @Test
    fun testGetCachedDogImages() {
        runBlocking {
            val dogImagesResponse = provideDogImagesResponse()
            val dog = Dog(dogBreedOne)
            whenever(cacheDataStore.get(dog.breed)).thenReturn(dogImagesResponse)
            val returnValue = dogRepositoryImpl.get(dog)
            assert(returnValue == listOf(DogImage(dogImageUrl)))
        }
    }

    @Test
    fun testGetDogImagesThrowsOnFailure() {
        runBlocking {
            val exception = IllegalArgumentException()
            val dog = Dog(dogBreedOne)
            whenever(remoteDataStore.get(dog.breed)).thenThrow(exception)

            var result: Exception? = null
            try {
                dogRepositoryImpl.get(dog)
            } catch (exception: IllegalArgumentException) {
                result = exception
            }
            assert(result == exception)
        }
    }

    // GET DOG IMAGES WITH SUB BREED
    @Test
    fun testGetRemoteDogImagesWithSubBreed() {
        runBlocking {
            val dogImagesResponse = provideDogImagesResponse()
            whenever(remoteDataStore.get(dogBreedOne, dogSubbreed)).thenReturn(dogImagesResponse)

            val returnValue = dogRepositoryImpl.get(Dog(dogBreedOne, dogSubbreed))
            val dogImage = DogImage(dogImageUrl)
            verify(remoteDataStore).get(dogBreedOne, dogSubbreed)
            assert(returnValue == listOf(dogImage))
        }
    }

    @Test
    fun testInsertDogImagesWithSubbreedToCache() {
        runBlocking {
            val dogImagesResponse = provideDogImagesResponse()
            val dog = Dog(dogBreedOne, dogSubbreed)
            whenever(remoteDataStore.get(dog.breed, dog.subBreed!!)).thenReturn(dogImagesResponse)
            dogRepositoryImpl.get(dog)
            verify(cacheDataStore).insert(dog.breed, dog.subBreed!!, dogImagesResponse)
        }
    }

    @Test
    fun testGetCachedDogImagesWithSubbreed() {
        runBlocking {
            val dogImagesResponse = provideDogImagesResponse()
            val dog = Dog(dogBreedOne, dogSubbreed)
            whenever(cacheDataStore.get(dog.breed, dog.subBreed!!)).thenReturn(dogImagesResponse)
            val returnValue = dogRepositoryImpl.get(dog)
            assert(returnValue == listOf(DogImage(dogImageUrl)))
        }
    }

    @Test
    fun testGetDogImagesWithSubbreedThrowsOnFailure() {
        runBlocking {
            val exception = IllegalArgumentException()
            val dog = Dog(dogBreedOne, dogSubbreed)
            whenever(remoteDataStore.get(dog.breed, dog.subBreed!!)).thenThrow(exception)

            var result: Exception? = null
            try {
                dogRepositoryImpl.get(dog)
            } catch (exception: IllegalArgumentException) {
                result = exception
            }
            assert(result == exception)
        }
    }

    // HELPERS
    private fun provideDogsResponse(): DogsResponse {
        val message: LinkedHashMap<String, List<String>?> = linkedMapOf()
        message[dogBreedOne] = null
        message[dogBreedTwo] = listOf(dogSubbreed)
        return DogsResponse(message)
    }

    private fun provideDogImagesResponse(): DogImagesResponse {
        val message = ArrayList<String>()
        message.add(dogImageUrl)
        return DogImagesResponse(message)
    }

    companion object {
        const val dogBreedOne = "hound"
        const val dogBreedTwo = "poodle"
        const val dogSubbreed = "french"
        const val dogImageUrl = "dog_image_url"
    }
}