package com.lost.domain.models

data class Dog(val breed: String, val subBreed: String?) {
    val name: String
        get() {
            return (if (subBreed != null) "$subBreed " else "") + breed
        }
}