package com.lost.dogs.features.dogs.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.lost.dogs.R
import com.lost.domain.models.Dog
import com.lost.tronald2020.features.shared.SingleLiveEvent
import kotlinx.android.synthetic.main.dog_item.view.*

/**
 * Recyclerview adapter to show list of dogs
 */
class DogsListAdapter : RecyclerView.Adapter<DogsListAdapter.ViewHolder?>() {
    private var dogs: List<Dog>? = null
    private var itemClicked = SingleLiveEvent<Dog>()

    fun setDogs(dogs: List<Dog>) {
        this.dogs = dogs
        notifyDataSetChanged()
    }

    fun itemClicked(): LiveData<Dog> = itemClicked

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.dog_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dogs?.get(position).let {
            holder.setDog(it)
        }
    }

    override fun getItemCount(): Int {
        return dogs?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var dog: Dog? = null

        fun setDog(dog: Dog?) {
            dog?.let {
                this.dog = it
                itemView.dog_item_textview.text = it.name
            }
        }

        init {
            itemView.setOnClickListener {
                itemClicked.value = dog
            }
        }
    }
}