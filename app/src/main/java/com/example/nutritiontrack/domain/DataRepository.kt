package com.example.nutritiontrack.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.nutritiontrack.database.AppDatabase
import com.example.nutritiontrack.database.mealsAsDomainModel
import com.example.nutritiontrack.network.FirebaseDataStore
import com.example.nutritiontrack.network.NetworkMeal
import com.example.nutritiontrack.network.mealAsDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.withContext


class DataRepository(database: AppDatabase, private val fireStoreDatabase: FirebaseDataStore) {

    val todayMeals: LiveData<List<Meal>> = Transformations.map(database.dao.getAllMeals()) {
        it.mealsAsDomainModel()
    }


    suspend fun getAllMeals() {
        withContext(Dispatchers.IO) {
            val meals = fireStoreDatabase.getMeals()
        }
    }


    //todo user information

    // network meal list

    // send and register new user


//    suspend fun print() {
//        val domainMeals = getMeals()?.mealAsDomainModel().
//        if (domainMeals != null)
//            for(meal in domainMeals)
//                Log.i("data", "${meal.sodium}")
//    }


//    suspend fun refreshMeals(){
//        withContext(Dispatchers.IO){
//            val searchedMeals = API.retrofitService.getMeals().await()
//            database.dao.insert(*searchedMeals.mea())
//        }
//    }
}