package com.example.nutritiontrack.util

import kotlin.math.roundToInt

const val WEIGHT_COST = 10
const val HEIGHT_COST = 6.25
const val AGE_COST = 5
const val MALE_FACTOR = 5
const val FEMALE_FACTOR = 161

enum class ActivityLevel(val lvl: Double) {
    Sedentary(1.2),
    Light(1.375),
    MODERATE(1.55),
    VERY(1.725),
    EXTRA(1.9)
}

enum class Gender(val lvl: Int) {
    Male(1),
    Female(2)
}

object StatusCalculator{
    fun calculateCalories(gender: Gender,activityLevel: ActivityLevel, weight: Double
                          , height: Int, age: Int) : Int {
        val calories = when (gender) {
            Gender.Male -> {
                (WEIGHT_COST * weight + HEIGHT_COST * height + AGE_COST * age + MALE_FACTOR) * activityLevel.lvl
            }
            Gender.Female -> {
                (WEIGHT_COST * weight + HEIGHT_COST * height + AGE_COST * age - FEMALE_FACTOR) * activityLevel.lvl
            }
        }
        return calories.roundToInt()
    }
}