package com.example.nutritiontrack.ui.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
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
import com.example.nutritiontrack.authentication.authUser
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
            if (!it) {
                binding.authHeader.text = getString(R.string.resgister)
                binding.firstName.isVisible = true
                binding.LastName.isVisible = true
                binding.activityLevelMenu.isVisible = true
                binding.genderMenu.isVisible = true
                binding.weight.isVisible = true
                binding.height.isVisible = true
                binding.age.isVisible = true
                binding.loginRegisterButton.text = getString(R.string.resgister)
                binding.authSwitchButton.text = getString(R.string.already_have_an_account)
            } else {
                binding.authHeader.text = getString(R.string.login)
                binding.firstName.isVisible = false
                binding.LastName.isVisible = false
                binding.activityLevelMenu.isVisible = false
                binding.genderMenu.isVisible = false
                binding.weight.isVisible = false
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

            if (getAuthenticationInstance() == null) {
                val email = binding.emailFieldText.text.toString().trim()
                val password = binding.passwordFieldText.text.toString().trim()
                if (viewModel.signInpageActive.value == false) {

                    val firstName = binding.firstNameText.toString().trim()
                    val lastname = binding.LastNameText.toString().trim()
                    val activityLevel = binding.activityLevelMenuText.toString().trim()
                    val gender = binding.genderMenuText.toString().trim()
                    val weight = binding.weightText.toString().trim()
                    val height = binding.heightText.toString().trim()
                    val age = binding.ageText.toString().trim()

                    if(validForCreateAccount(binding)) {
                        createAccount(email, password)
                    }

                    // TODO make the button load with a spinning weel to indicate that it's loading
                    // TODO add the data to the data base from all the other fields
                } else {
                    viewModel.modelSignIn(email, password)
                    //TODO get all the data from the database
                }
            }
        }
        /**
         * Navigate to home fragment after the live data changes
         * due to the sign in or create account call
         * **/
        authUser.observe(viewLifecycleOwner, Observer { firebaseUser ->
            if (firebaseUser != null) {
                findNavController().navigate(AuthDirections.actionAuthToHome2())
            }
        })
        return binding.root
    }

    private fun validForCreateAccount(binding: AuthFragmentBinding) : Boolean{
        val firstName = binding.firstNameText.text.toString().trim()
        val lastname = binding.LastNameText.text.toString().trim()
        val activityLevel = binding.activityLevelMenuText.text.toString().trim()
        val gender = binding.genderMenuText.text.toString().trim()
        val weight = binding.weightText.text.toString().trim()
        val height = binding.heightText.text.toString().trim()
        val age = binding.ageText.text.toString().trim()

        if(firstName.isEmpty()) {
            binding.firstName.isErrorEnabled = true
            binding.firstName.error = getString(R.string.text_field_empty)
            return false
        }else{
            binding.firstName.isErrorEnabled = false
        }
        if(lastname.isEmpty()) {
            binding.LastName.isErrorEnabled = true
            binding.LastName.error = getString(R.string.text_field_empty)
            return false
        }else{
            binding.LastName.isErrorEnabled = false
        }

        validForSignIn(binding)

        if(activityLevel.isEmpty()) {
            binding.activityLevelMenu.isErrorEnabled = true
            binding.activityLevelMenu.error = getString(R.string.text_field_empty)
            return false
        }else{
            binding.activityLevelMenu.isErrorEnabled = false
        }
        if(gender.isEmpty()) {
            binding.genderMenu.isErrorEnabled = true
            binding.genderMenu.error = getString(R.string.text_field_empty)
            return false
        }else{
            binding.genderMenu.isErrorEnabled = false
        }
        if(weight.isEmpty()) {
            binding.weight.isErrorEnabled = true
            binding.weight.error = getString(R.string.text_field_empty)
            return false
        }else{
            binding.weight.isErrorEnabled = false
        }
        if(height.isEmpty()) {
            binding.height.isErrorEnabled = true
            binding.height.error = getString(R.string.text_field_empty)
            return false
        }else{
            binding.height.isErrorEnabled = false
        }
        if(age.isEmpty()) {
            binding.age.isErrorEnabled = true
            binding.age.error = getString(R.string.text_field_empty)
            return false

        }else{
            binding.age.isErrorEnabled = false
        }
        return true
    }

    private fun validForSignIn(binding: AuthFragmentBinding): Boolean {
        val email = binding.emailFieldText.text.toString().trim()
        val password = binding.passwordFieldText.text.toString().trim()
        if(email.isEmpty()) {
            binding.emailField.isErrorEnabled = true
            binding.emailField.error = getString(R.string.text_field_empty)
            return false
        }else if(getSignInEmailValid(email)){
            binding.emailField.isErrorEnabled = true
            binding.emailField.error = getString(R.string.invalid_email)
            return false
        }else {
            binding.emailField.isErrorEnabled = false
        }
        if(password.isEmpty()) {
            binding.passwordField.isErrorEnabled = true
            binding.passwordField.error = getString(R.string.text_field_empty)
            return false
        }else{
            binding.passwordField.isErrorEnabled = false
        }
        return true
    }

    private fun getSignInEmailValid(email: String) =
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()


    //add this as a binding adapter
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