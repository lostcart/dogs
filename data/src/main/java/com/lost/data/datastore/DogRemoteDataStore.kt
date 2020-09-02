package com.lost.data.datastore

import com.lost.data.api.DogApiService
import com.lost.data.models.DogImagesResponse
import com.lost.data.models.DogsResponse
import javax.inject.Inject

internal class DogRemoteDataStore @Inject constructor(
    private val dogApiService: DogApiService
) : DogDataStore.Remote {

    override suspend fun get(): DogsResponse {
        return dogApiService.get()
    }

    override suspend fun get(dog: String): DogImagesResponse {
        return dogApiService.get(dog)
    }

    override suspend fun get(dogBreed: String, dogSubBreed: String): DogImagesResponse {
        return dogApiService.get(dogBreed, dogSubBreed)
    }
}