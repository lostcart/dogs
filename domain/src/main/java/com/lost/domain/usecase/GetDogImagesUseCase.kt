package com.lost.domain.usecase

import com.lost.domain.models.Dog
import com.lost.domain.repository.DogRepository
import javax.inject.Inject

class GetDogImagesUseCase @Inject constructor(private val repository: DogRepository) {

    suspend operator fun invoke(dog: Dog) = repository.get(dog)
}