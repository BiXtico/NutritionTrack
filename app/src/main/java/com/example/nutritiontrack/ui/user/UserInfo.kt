package com.example.nutritiontrack.ui.user

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.nutritiontrack.R
import com.example.nutritiontrack.databinding.AboutFragmentBinding
import com.example.nutritiontrack.databinding.UserInfoFragmentBinding

class UserInfo : Fragment() {

    companion object {
        fun newInstance() = UserInfo()
    }

    private lateinit var viewModel: UserInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : UserInfoFragmentBinding = DataBindingUtil.inflate(layoutInflater
            ,R.layout.user_info_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}