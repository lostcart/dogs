package com.lost.dogs.features.dogs.list

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.lost.dogs.R
import com.lost.dogs.features.shared.base.BaseFragment
import com.lost.domain.models.Dog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dogs_list.*
import javax.inject.Inject

@AndroidEntryPoint
class DogsListFragment : BaseFragment() {
    private val dogsListAdapter = DogsListAdapter()

    @Inject
    lateinit var viewModel: DogsListViewModel

    override val layoutId: Int
        get() = R.layout.dogs_list

    override fun setupViews() {
        dogs_list_recyclerview.adapter = dogsListAdapter
        dogsListAdapter.itemClicked().observe(viewLifecycleOwner, Observer(::showDogDetails))
        dogs_list_swipe_refresh.setOnRefreshListener {
            viewModel.init()
        }
    }

    override fun setupViewModel() {
        viewModel.dogsList().observe(viewLifecycleOwner, Observer(::showDogs))
        viewModel.isLoading().observe(viewLifecycleOwner, Observer(::showLoading))
        viewModel.error().observe(viewLifecycleOwner, Observer(::showError))
        viewModel.init()
    }

    private fun showDogs(dogs: List<Dog>) {
        dogsListAdapter.setDogs(dogs)
    }

    private fun showLoading(isLoading: Boolean) {
        dogs_list_swipe_refresh.isRefreshing = isLoading
    }

    private fun showError(throwable: Throwable) {
        context?.let {
            Toast.makeText(it, throwable.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDogDetails(dog: Dog?) {
        dog?.let {
            findNavController().navigate(DogsListFragmentDirections.actionDogsToDetail(it))
        }
    }
}