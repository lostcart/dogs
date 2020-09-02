package com.lost.dogs.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.lost.dogs.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val navController by lazy { findNavController(main_fragment_container.id) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}