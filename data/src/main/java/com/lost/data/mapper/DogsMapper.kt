package com.lost.data.mapper

import com.lost.data.models.DogsResponse
import com.lost.domain.models.Dog
import javax.inject.Inject

internal class DogsMapper @Inject constructor() : Mapper<DogsResponse?, List<Dog>>() {

    override fun mapFrom(dogsResponse: DogsResponse?): List<Dog> {
        val dogs = ArrayList<Dog>()
        dogsResponse?.message?.forEach { breeds ->
            dogs.add(Dog(breeds.key, null))
            // Add all sub breeds as a new dog entry
            breeds.value?.forEach {
                dogs.add(Dog(breeds.key, it))
            }
        }
        return dogs
    }
}