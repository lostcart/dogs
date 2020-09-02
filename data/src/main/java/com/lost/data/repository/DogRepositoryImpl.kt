package com.lost.data.repository

import com.lost.data.datastore.DogDataStore
import com.lost.data.mapper.DogImagesMapper
import com.lost.data.mapper.DogsMapper
import com.lost.data.models.DogImagesResponse
import com.lost.data.models.DogsResponse
import com.lost.domain.models.Dog
import com.lost.domain.models.DogImage
import com.lost.domain.repository.DogRepository
import javax.inject.Inject

internal class DogRepositoryImpl @Inject constructor(
    private val remote: DogDataStore.Remote,
    private val cache: DogDataStore.Cache,
    private val dogsMapper: DogsMapper,
    private val dogImagesMapper: DogImagesMapper
) : DogRepository {

    override suspend fun get(): List<Dog>? {
        var source = cache.get()
        if (source == null) {
            runCatching {
                source = remote.get().also(cache::insert)
            }.onFailure { error ->
                throw error
            }
        }
        return dogsMapper.mapFrom(source)
    }

    override suspend fun get(dog: Dog): List<DogImage>? {
        var source: DogImagesResponse? = null
        runCatching {
            val subBreed = dog.subBreed
            source = if (subBreed != null) {
                cache.get(dog.breed, subBreed) ?: remote.get(dog.breed, subBreed)
                    .also { cache.insert(dog.breed, subBreed, it) }
            } else {
                cache.get(dog.breed) ?: remote.get(dog.breed).also { cache.insert(dog.breed, it) }
            }
        }.onFailure { error ->
            throw error
        }
        return dogImagesMapper.mapFrom(source)
    }
}