package com.lost.data.datastore

import com.lost.data.models.DogImagesResponse
import com.lost.data.models.DogsResponse
import javax.inject.Inject

internal class DogCacheDataStore @Inject constructor() : DogDataStore.Cache {
    private var dogsResponse: DogsResponse? = null
    private val dogImageResponse: LinkedHashMap<String, DogImagesResponse> = linkedMapOf()

    override fun get(): DogsResponse? {
        return dogsResponse
    }

    override fun get(dog: String): DogImagesResponse? {
        return dogImageResponse[dog]
    }

    override fun get(dogBreed: String, dogSubBreed: String): DogImagesResponse? {
        return dogImageResponse[dogBreed + dogSubBreed]
    }

    override fun insert(response: DogsResponse) {
        dogsResponse = response
    }

    override fun insert(dog: String, response: DogImagesResponse) {
        dogImageResponse[dog] = response
    }

    override fun insert(dogBreed: String, dogSubBreed: String, response: DogImagesResponse) {
        dogImageResponse[dogBreed + dogSubBreed] = response
    }
}