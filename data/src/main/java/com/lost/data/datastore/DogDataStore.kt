package com.lost.data.datastore

import com.lost.data.models.DogImagesResponse
import com.lost.data.models.DogsResponse

internal interface DogDataStore {

    interface Cache {
        fun get(): DogsResponse?
        fun get(dog: String): DogImagesResponse?
        fun get(dogBreed: String, dogSubBreed: String): DogImagesResponse?
        fun insert(response: DogsResponse)
        fun insert(dog: String, response: DogImagesResponse)
        fun insert(dogBreed: String, dogSubBreed: String, response: DogImagesResponse)
    }

    interface Remote {
        suspend fun get(): DogsResponse
        suspend fun get(dog: String): DogImagesResponse
        suspend fun get(dogBreed: String, dogSubBreed: String): DogImagesResponse
    }
}