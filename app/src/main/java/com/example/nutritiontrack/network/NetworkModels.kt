package com.example.nutritiontrack.network

import android.util.Log
import androidx.lifecycle.Transformations.map
import com.example.nutritiontrack.database.DatabaseMeal
import com.example.nutritiontrack.domain.Meal
import com.example.nutritiontrack.util.ActivityLevel
import com.example.nutritiontrack.util.Gender
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class NetworkMeal(
    var mealId: Int,
    val name: String,
    val nutrition: List<Double>,
)


data class NetworkUser(
    var id: Int,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val activityLevel: ActivityLevel,
    val weight: Int,
    val height: Int,
    val age: Int
)

fun List<NetworkMeal>.mealAsDomainModel():List<Meal>{
    Log.i("inNetworkModel", "mealAsDomainModel called for conversion")
    return map{
        Meal(
            name = it.name,
            mealId = it.mealId,
            calories = it.nutrition[0],
            amount = 0.0,
            totalFat = it.nutrition[1],
            suger = it.nutrition[2],
            sodium = it.nutrition[3],
            protein = it.nutrition[4],
            sateratedFat = it.nutrition[5]
        )
    }
}

fun List<NetworkMeal>.mealAsDatabaseModel(): Array<DatabaseMeal>{
    return map {
        DatabaseMeal(
            name = it.name,
            mealId = it.mealId,
            calories = it.nutrition[0],
            amount = 0.0,
            totalFat = it.nutrition[1],
            suger = it.nutrition[2],
            sodium = it.nutrition[3],
            protein = it.nutrition[4],
            sateratedFat = it.nutrition[5]
        )
    }.toTypedArray()
}



