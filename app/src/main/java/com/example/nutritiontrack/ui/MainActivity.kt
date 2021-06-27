package com.example.nutritiontrack.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.nutritiontrack.R
import com.example.nutritiontrack.databinding.ActivityMainBinding
import com.example.nutritiontrack.ui.home.HomeDirections
import com.example.nutritiontrack.ui.recommendations.RecommendationsDirections
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        //setting the app bar with the navigation graph
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        setSupportActionBar(binding.appBar)
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(setOf(R.id.home2, R.id.recommendations))
        binding.appBar.setupWithNavController(navController, appBarConfiguration)

        //setting the bottom navigation with the navigation controller
        binding.bottomNavigation.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { _: NavController, nd: NavDestination, _: Bundle? ->
            if (nd.id == R.id.recommendations || nd.id == R.id.home2) {
                supportActionBar!!.show()
                binding.bottomNavigation.isVisible = true
            } else if (nd.id == R.id.auth) {
                supportActionBar!!.hide()
                binding.bottomNavigation.isVisible = false
            } else {
                supportActionBar!!.show()
                binding.bottomNavigation.isVisible = false
            }
        }
    }
    //setting the navigation graph with the options menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item!!,
            navController
        ) || super.onOptionsItemSelected(item)
    }
}
