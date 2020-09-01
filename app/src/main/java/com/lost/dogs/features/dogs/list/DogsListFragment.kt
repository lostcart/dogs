package com.lost.dogs.features.dogs.list

import com.lost.dogs.R
import com.lost.dogs.features.shared.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DogsListFragment : BaseFragment() {
    @Inject
    lateinit var viewModel: DogsListViewModel

    override val layoutId: Int
        get() = R.layout.dogs_list

    override fun setupViews() {
    }

    override fun setupViewModel() {
        viewModel.init()
    }
}