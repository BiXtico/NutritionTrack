package com.example.nutritiontrack.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.nutritiontrack.R
import com.example.nutritiontrack.databinding.HomeFragmentBinding
import com.google.android.material.appbar.AppBarLayout

class Home : Fragment() {

    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : HomeFragmentBinding = DataBindingUtil.inflate(layoutInflater
            ,R.layout.home_fragment, container, false)

        viewModel= ViewModelProvider(this).get(HomeViewModel::class.java)
        val adapter = MealListAdapter(MealClickListener {
            mealId ->  //to do (navigate to to the meal page to display the needed meal)
        })
        binding.lifecycleOwner = this
        binding.mealList.adapter = adapter

        viewModel.calories.observe(viewLifecycleOwner, Observer { newCalories ->
            binding.textView.text = newCalories.toString()
        })
        //add the viewmodel data of the adapter to be set again

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

}