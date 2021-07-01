package com.example.nutritiontrack.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutritiontrack.database.getInstance
import com.example.nutritiontrack.domain.DataRepository
import com.example.nutritiontrack.domain.Meal
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Response

class HomeViewModel(app: Application) : ViewModel() {

    private val database = getInstance(app)
    private val repository = DataRepository(database)


    private val _calories = MutableLiveData<String?>()
    val calories : LiveData<String?>
        get() = _calories

    private val _addedMeals = MutableLiveData<MutableList<Meal>?>()
    val addedMeals :LiveData<MutableList<Meal>?>
        get() = _addedMeals

    init {
        _addedMeals.value = null
        _calories.value = ""
    }






}