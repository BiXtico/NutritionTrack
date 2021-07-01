package com.example.nutritiontrack.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.nutritiontrack.R
import com.example.nutritiontrack.databinding.SearchFragmentBinding
import com.example.nutritiontrack.util.RessultListAdapter
import kotlin.concurrent.timer

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

        val adapter = RessultListAdapter()
        binding.searchResult.adapter = adapter

        viewModel.searchedMeals.observe(viewLifecycleOwner, Observer { meals ->
            meals?.let { adapter.submitList(meals) }
        })

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
                Toast.makeText(context , "hello there", Toast.LENGTH_SHORT).show()
            }
        })
        return binding.root
    }


}