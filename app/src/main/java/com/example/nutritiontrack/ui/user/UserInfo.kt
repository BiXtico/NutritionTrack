package com.example.nutritiontrack.ui.user

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import com.example.nutritiontrack.R
import com.example.nutritiontrack.databinding.UserInfoFragmentBinding
import com.example.nutritiontrack.util.ActivityLevel
import com.example.nutritiontrack.util.Gender

class UserInfo : Fragment() {

    private lateinit var viewModel: UserInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : UserInfoFragmentBinding = DataBindingUtil.inflate(layoutInflater
            ,R.layout.user_info_fragment, container, false)
        itemsToTextField(binding)
        return binding.root
    }

    private fun itemsToTextField(binding: UserInfoFragmentBinding) {
        val genderList = listOf(Gender.Female, Gender.Male)
        val activityLevelList = listOf(
            ActivityLevel.Sedentary, ActivityLevel.Light, ActivityLevel.MODERATE
            , ActivityLevel.VERY, ActivityLevel.EXTRA)
        val genderAdapter = ArrayAdapter(requireContext(), R.layout.exposed_dropdown_menu_item, genderList)
        val activityLevelAdapter = ArrayAdapter(requireContext(), R.layout.exposed_dropdown_menu_item, activityLevelList)
        (binding.genderMenu.editText as? AutoCompleteTextView)?.setAdapter(genderAdapter)
        (binding.activityLevelMenu.editText as? AutoCompleteTextView)?.setAdapter(activityLevelAdapter)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}