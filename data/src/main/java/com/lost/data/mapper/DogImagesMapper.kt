package com.lost.data.mapper

import com.lost.data.models.DogImagesResponse
import com.lost.domain.models.DogImage
import javax.inject.Inject

internal class DogImagesMapper @Inject constructor() : Mapper<DogImagesResponse?, List<DogImage>>() {

    override fun mapFrom(dogImagesResponse: DogImagesResponse?): List<DogImage> {
        val dogImages = ArrayList<DogImage>()
        dogImagesResponse?.message?.forEach {
            dogImages.add(DogImage(it))
        }
        return dogImages
    }
}