package com.lost.dogs.features.base

import javax.inject.Inject

class TestUseCase @Inject constructor() {
    operator fun invoke() = "test"
}