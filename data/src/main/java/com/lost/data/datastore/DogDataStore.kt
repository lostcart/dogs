package com.lost.data.datastore

import com.lost.data.models.DogImagesResponse
import com.lost.data.models.DogsResponse

internal interface DogDataStore {

    interface Local {
    }

    interface Remote {
        suspend fun get(): DogsResponse?
        suspend fun get(dog: String): DogImagesResponse?
    }
}