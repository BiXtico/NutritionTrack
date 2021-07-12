package com.example.nutritiontrack.domain

import com.example.nutritiontrack.util.ActivityLevel
import com.example.nutritiontrack.util.Gender


data class Meal(
    var mealId: Int = 0,
    val name: String,
    val calories: Double,
    var amount: Double,
    val totalFat: Double,
    val suger: Double,
    val sodium: Double,
    val protein: Double,
    val sateratedFat: Double
)

data class User(
    var id: Int = 0,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val activityLevel: ActivityLevel,
    val weight: Int,
    val height: Int,
    val age: Int
)