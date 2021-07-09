package com.example.nutritiontrack.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.nutritiontrack.database.AppDatabase
import com.example.nutritiontrack.database.mealsAsDomainModel
import com.example.nutritiontrack.network.getMeals
import com.example.nutritiontrack.network.mealAsDomainModel
import com.google.firebase.firestore.FirebaseFirestore


class DataRepository(database: AppDatabase) {

    val todayMeals: LiveData<List<Meal>> = Transformations.map(database.dao.getAllMeals()) {
        it.mealsAsDomainModel()
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