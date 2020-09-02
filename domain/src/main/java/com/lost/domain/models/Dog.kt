package com.lost.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dog(val breed: String, val subBreed: String? = null): Parcelable {

    override fun toString(): String {
        return (if (subBreed != null) "$subBreed " else "") + breed
    }
}