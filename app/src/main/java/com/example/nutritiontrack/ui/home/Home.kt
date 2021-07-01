package com.example.nutritiontrack.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.nutritiontrack.R
import com.example.nutritiontrack.databinding.HomeFragmentBinding
import com.example.nutritiontrack.ui.search.SearchViewModel
import com.example.nutritiontrack.ui.search.SearchViewModelFactory


class Home : Fragment() {

    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: HomeFragmentBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.home_fragment, container, false
        )


        viewModel = ViewModelProvider(this, HomeViewModelFactory(requireActivity().application)).get(HomeViewModel::class.java)
        val adapter =
            MealListAdapter(MealClickListener { mealId ->  //to do (navigate to to the meal page to display the needed meal)
            })

        viewModel.authenticated.observe(viewLifecycleOwner, Observer { firebaseUser ->
            if(firebaseUser == null){
                findNavController().navigate(HomeDirections.actionHome2ToAuth())
            }else{
                //TODO get and show information
            }
        })

        binding.addButton.setOnClickListener {
            this.findNavController().navigate(HomeDirections.actionHome2ToSearch())
        }

        binding.lifecycleOwner = this
        binding.mealList.adapter = adapter



//        viewModel.calories.observe(viewLifecycleOwner, Observer { newCalories ->
//            binding.textView.text = newCalories.toString()
//        })
        //add the viewmodel data of the adapter to be set again

        return binding.root
    }


}