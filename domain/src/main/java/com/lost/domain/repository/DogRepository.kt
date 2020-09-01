package com.lost.domain.repository

import com.lost.domain.models.Dog
import com.lost.domain.models.DogImages

interface DogRepository {
    suspend fun get(): List<Dog>?
    suspend fun get(dog: Dog): List<DogImages>?
}