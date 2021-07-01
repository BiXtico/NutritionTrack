package com.example.nutritiontrack.ui.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.nutritiontrack.R
import com.example.nutritiontrack.authentication.createAccount
import com.example.nutritiontrack.authentication.getAuthenticationInstance
import com.example.nutritiontrack.authentication.signIn
import com.example.nutritiontrack.databinding.AuthFragmentBinding
import com.example.nutritiontrack.util.ActivityLevel
import com.example.nutritiontrack.util.Gender


class Auth : Fragment() {

    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: AuthFragmentBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.auth_fragment, container, false
        )
        itemsToTextField(binding)
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        viewModel.signInpageActive.observe(viewLifecycleOwner, Observer {
            if(!it){
                binding.authHeader.text = getString(R.string.resgister)
                binding.firstName.isVisible = true
                binding.LastName.isVisible = true
                binding.activityLevelMenu.isVisible = true
                binding.genderMenu.isVisible = true
                binding.weight.isVisible= true
                binding.height.isVisible = true
                binding.age.isVisible = true
                binding.loginRegisterButton.text = getString(R.string.resgister)
                binding.authSwitchButton.text = getString(R.string.already_have_an_account)
            }else{
                binding.authHeader.text = getString(R.string.login)
                binding.firstName.isVisible = false
                binding.LastName.isVisible = false
                binding.activityLevelMenu.isVisible = false
                binding.genderMenu.isVisible = false
                binding.weight.isVisible= false
                binding.height.isVisible = false
                binding.age.isVisible = false
                binding.loginRegisterButton.text = getString(R.string.login)
                binding.authSwitchButton.text = getString(R.string.does_not_have_an_account)
            }
        })

        binding.authSwitchButton.setOnClickListener {
            viewModel.switchToSignIn()
        }

        binding.loginRegisterButton.setOnClickListener {
            val email = binding.emailField.text.toString().trim()
            val password = binding.passwordField.text.toString().trim()

            //TODO handle wrong or empty information or string
            if (getAuthenticationInstance() == null) {
                if(viewModel.signInpageActive.value == false){
                    createAccount(email, password)
                    // TODO add the data to the data base from all the other fields
                    // TODO store the information in the database
                }else{
                    viewModel.modelSignIn(email, password)
                    //TODO get all the data from the database
                }
            }
        }

        /**
         * Navigate to home fragment after the live data changes
         * due to the sign in or create account call
         * **/
        viewModel.navigateHome.observe(viewLifecycleOwner, Observer{ firebaseUser ->
            if (firebaseUser) {
                findNavController().navigate(AuthDirections.actionAuthToHome2())
                viewModel.doneNavigating()
            }
        })
        return binding.root
    }

    private fun itemsToTextField(binding: AuthFragmentBinding) {
        val genderList = listOf(Gender.Female, Gender.Male)
        val activityLevelList = listOf(
            ActivityLevel.Sedentary,
            ActivityLevel.Light,
            ActivityLevel.MODERATE,
            ActivityLevel.VERY,
            ActivityLevel.EXTRA
        )
        val genderAdapter =
            ArrayAdapter(requireContext(), R.layout.exposed_dropdown_menu_item, genderList)
        val activityLevelAdapter =
            ArrayAdapter(requireContext(), R.layout.exposed_dropdown_menu_item, activityLevelList)
        (binding.genderMenu.editText as? AutoCompleteTextView)?.setAdapter(genderAdapter)
        (binding.activityLevelMenu.editText as? AutoCompleteTextView)?.setAdapter(
            activityLevelAdapter
        )
    }

}