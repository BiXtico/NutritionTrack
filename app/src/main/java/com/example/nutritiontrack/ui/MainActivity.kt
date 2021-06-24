package com.example.nutritiontrack.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.nutritiontrack.R
import com.example.nutritiontrack.databinding.ActivityMainBinding
import com.example.nutritiontrack.ui.auth.AuthDirections
import com.example.nutritiontrack.ui.home.HomeDirections
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.BaseTransientBottomBar

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var bottomAppBar: BottomNavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomAppBar = binding.bottomNavigation
        appBarConfiguration = AppBarConfiguration(setOf(R.id.home2,R.id.recommendations,R.id.userInfo))
        binding.appBar.setupWithNavController(navController,appBarConfiguration)
        }


    }