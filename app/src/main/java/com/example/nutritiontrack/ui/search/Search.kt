package com.example.nutritiontrack.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.nutritiontrack.R
import com.example.nutritiontrack.databinding.SearchFragmentBinding
import com.example.nutritiontrack.ui.home.MealClickListener
import com.example.nutritiontrack.util.ResultListAdapter

class Search : Fragment() {

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: SearchFragmentBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.search_fragment, container, false
        )
        viewModel = ViewModelProvider(this,SearchViewModelFactory(requireActivity().application)).get(SearchViewModel::class.java)
        binding.lifecycleOwner = this

        val adapter = ResultListAdapter(MealClickListener { 
            mealId ->  findNavController().navigate(SearchDirections.actionSearchToMeal(mealId))
        })
        binding.searchResult.adapter = adapter

        viewModel.searchedMeals.observe(viewLifecycleOwner, Observer { meals ->
            meals?.let { adapter.submitList(meals) }
        })


//        viewModel.searchedMeals.observe(viewLifecycleOwner, { meals ->
//            if(meals != null){
//                Log.i("fineData", "This is meals ${meals.elementAt(0).calories}")
//            }
//        })

        binding.SearchTextEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                val text = binding.SearchTextEdit.text.toString()
                //TODO send the text to the firestore to get data according to the text


            }
        })
        return binding.root
    }


}