package com.lost.dogs.features.dogs.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lost.dogs.R
import com.lost.dogs.features.shared.images.ImageLoader
import com.lost.domain.models.Dog
import com.lost.domain.models.DogImage
import com.lost.tronald2020.features.shared.SingleLiveEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dog_image_item.view.*
import kotlinx.android.synthetic.main.dog_item.view.*
import javax.inject.Inject

/**
 * Recyclerview adapter to show list of dog images
 */
class DogDetailsAdapter : RecyclerView.Adapter<DogDetailsAdapter.ViewHolder?>() {
    private var dogImages: List<DogImage>? = null

    fun setDogImages(dogImages: List<DogImage>?) {
        this.dogImages = dogImages
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.dog_image_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dogImages?.get(position).let {
            holder.setDogImage(it)
        }
    }

    override fun getItemCount(): Int {
        return dogImages?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setDogImage(dogImage: DogImage?) {
            dogImage?.let {
                ImageLoader.loadImage(it.url, itemView.dog_item_imageview)
            }
        }
    }
}