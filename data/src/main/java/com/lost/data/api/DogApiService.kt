package com.lost.data.api

import com.lost.data.models.DogImagesResponse
import com.lost.data.models.DogsResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface DogApiService {
    @GET("breeds/list/all")
    suspend fun get(): DogsResponse

    @GET("breed/{dog_breed}/images")
    suspend fun get(@Path("dog_breed") dogBreed: String): DogImagesResponse
}