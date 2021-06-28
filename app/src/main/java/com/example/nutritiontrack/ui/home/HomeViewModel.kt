package com.example.nutritiontrack.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutritiontrack.database.Meal
import com.example.nutritiontrack.network.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _calories = MutableLiveData<String?>()
    val calories : LiveData<String?>
        get() = _calories

    private val _addedMeals = MutableLiveData<MutableList<Meal>?>()
    val addedMeals :LiveData<MutableList<Meal>?>
        get() = _addedMeals

    init {
        _addedMeals.value = null
        _calories.value = ""
        getAllData()
    }

    private fun setCalories(){
//        _addedMeals.value?.forEach { it ->
//            _calories.value?.minus(it.calories)
//        }
    }
    private fun getAllData(){
        API.retrofitService.getMeals().enqueue(object: Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                _calories.value = response.body()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                _calories.value = "Failure" + t.message
            }
        })
    }

}