package com.lost.data.repository

import com.lost.data.datastore.DogDataStore
import com.lost.data.mapper.DogsMapper
import com.lost.data.models.DogsResponse
import com.lost.domain.models.Dog
import com.lost.domain.models.DogImages
import com.lost.domain.repository.DogRepository
import javax.inject.Inject

internal class DogRepositoryImpl @Inject constructor(
    private val remote: DogDataStore.Remote,
    private val dogsMapper: DogsMapper
) : DogRepository {

    override suspend fun get(): List<Dog>? {
        var source: DogsResponse? = null
        runCatching {
            source = remote.get()
        }.onFailure { error ->
            throw error
        }
        return dogsMapper.mapFrom(source)
    }

    override suspend fun get(dog: Dog): List<DogImages>? {
        TODO("Not yet implemented")
    }
}