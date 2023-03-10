package com.gultendogan.pixabayapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gultendogan.pixabayapp.R
import dagger.hilt.android.AndroidEntryPoint
import com.gultendogan.pixabayapp.common.base.ui.BaseActivity
import com.gultendogan.pixabayapp.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val _BindingInflater: (LayoutInflater) -> ActivityMainBinding
            by lazy { ActivityMainBinding::inflate }

    private val vM by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initNavController(binding.fragmentContainerView)
        initToolbar(binding.toolbar)


        vM.toolbarLiftedObservable.observe(this) {
            binding.appBarLayout.setLiftable(it )
        }



    }

    override fun onBackPressed() {
        if (navController.previousBackStackEntry == null)
            finish()
        else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}