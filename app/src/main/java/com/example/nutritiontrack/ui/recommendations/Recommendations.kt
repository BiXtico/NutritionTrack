package com.example.nutritiontrack.ui.recommendations

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.nutritiontrack.R
import com.example.nutritiontrack.databinding.RecommendationsFragmentBinding

class Recommendations : Fragment() {

    private lateinit var viewModel: RecommendationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : RecommendationsFragmentBinding = DataBindingUtil.inflate(layoutInflater
            ,R.layout.recommendations_fragment, container, false)
        viewModel = ViewModelProvider(this).get(RecommendationsViewModel::class.java)
        binding.lifecycleOwner = this
        return binding.root
    }


}