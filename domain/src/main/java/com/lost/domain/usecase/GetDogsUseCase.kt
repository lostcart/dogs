package com.lost.domain.usecase

import com.lost.domain.repository.DogRepository
import javax.inject.Inject

class GetDogsUseCase @Inject constructor(private val repository: DogRepository) {

    suspend operator fun invoke() = repository.get()
}