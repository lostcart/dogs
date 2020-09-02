package com.lost.dogs.features.dogs.details

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.lost.dogs.R
import com.lost.dogs.features.shared.base.BaseFragment
import com.lost.domain.models.DogImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dog_details.*
import javax.inject.Inject

@AndroidEntryPoint
class DogDetailsFragment : BaseFragment() {
    private val dogDetailsAdapter = DogDetailsAdapter()
    private val args by navArgs<DogDetailsFragmentArgs>()

    @Inject
    lateinit var viewModel: DogDetailsViewModel

    override val layoutId: Int
        get() = R.layout.dog_details

    override fun setupViews() {
        dog_details_recyclerview.adapter = dogDetailsAdapter
    }

    override fun setupViewModel() {
        viewModel.imageUrls().observe(viewLifecycleOwner, Observer(::showDogs))
        viewModel.isLoading().observe(viewLifecycleOwner, Observer(::showLoading))
        viewModel.error().observe(viewLifecycleOwner, Observer(::showError))
        viewModel.init(args.dog)
    }

    private fun showDogs(dogImages: List<DogImage>) {
        dogDetailsAdapter.setDogImages(dogImages)
    }

    private fun showLoading(isLoading: Boolean) {
        dog_details_viewflipper.displayedChild = if (isLoading) INDEX_LOADING else INDEX_CONTENT
    }

    private fun showError(throwable: Throwable) {
        context?.let {
            Toast.makeText(it, throwable.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val INDEX_LOADING = 0
        const val INDEX_CONTENT = 1
    }
}