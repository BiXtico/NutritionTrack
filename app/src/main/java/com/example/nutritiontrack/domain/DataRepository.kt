package com.example.nutritiontrack.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.nutritiontrack.database.AppDatabase
import com.example.nutritiontrack.database.mealsAsDomainModel
import com.example.nutritiontrack.network.API
import com.example.nutritiontrack.network.NetworkMealContainer
import com.example.nutritiontrack.network.mealAsDomainModel
import com.google.firebase.auth.FirebaseAuth


class DataRepository(database: AppDatabase) {

    val todayMeals: LiveData<List<Meal>> = Transformations.map(database.dao.getAllMeals()) {
        it.mealsAsDomainModel()
    }

    suspend fun getSearchableItems(): List<Meal> {
        Log.i("in Repository", "getSearchableItems() called")

        val meals: NetworkMealContainer = API.retrofitService.getMealsAsync().await()
        return meals.mealAsDomainModel()
    }


//    private fun getAllData() : MutableLiveData<List<NetworkMeal>?> {
//        val data = MutableLiveData<List<NetworkMeal>?>()
//        API.retrofitService.getMealsAsync().enqueue(object: Callback<List<NetworkMeal>> {
//
//            override fun onResponse(
//                call: Call<List<NetworkMeal>>,
//                response: Response<List<NetworkMeal>>
//            ){
//                data.value = response.body()
//            }
//
//            override fun onFailure(call: Call<List<NetworkMeal>>, t: Throwable) {
//                //TODO display metwork error problem
//            }
//        })
//        return data
//    }


//    suspend fun refreshMeals(){
//        withContext(Dispatchers.IO){
//            val searchedMeals = API.retrofitService.getMeals().await()
//            database.dao.insert(*searchedMeals.mea())
//        }
//    }
}