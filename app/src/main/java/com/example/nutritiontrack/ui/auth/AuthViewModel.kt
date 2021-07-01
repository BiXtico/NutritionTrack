package com.example.nutritiontrack.ui.auth

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutritiontrack.authentication.getAuthenticationInstance
import com.example.nutritiontrack.authentication.signIn

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthViewModel() : ViewModel() {


    private val _navigateHome = MutableLiveData<Boolean>()
    val navigateHome: LiveData<Boolean>
        get() = _navigateHome


    private val _signInPageActive = MutableLiveData<Boolean>()
    val signInpageActive: LiveData<Boolean>
        get() = _signInPageActive

    init {
        _signInPageActive.value = false
        _navigateHome.value = false
    }

    fun modelSignIn(email :String, password : String){
        signIn(email,password)
        if (getAuthenticationInstance() !=null){
            _navigateHome.value = true
            Log.i("Firebase", "createUserWithEmail:failure")
        }
    }

    fun doneNavigating(){
        _navigateHome.value = false
    }

    fun switchToSignIn(){
        _signInPageActive.value = _signInPageActive.value != true
    }


}