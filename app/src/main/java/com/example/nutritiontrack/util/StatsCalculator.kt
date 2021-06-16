package com.example.nutritiontrack.util

import kotlin.math.roundToInt

const val WEIGHT_COST = 10
const val HEIGHT_COST = 6.25
const val AGE_COST = 5
const val MALE_FACTOR = 5
const val FEMALE_FACTOR = 161

enum class ActivityLevel(val lvl: Double) {
    SEDENTARY(1.2),
    LIGHTLY_ACTIVE(1.375),
    MODERATELY_ACTIVE(1.55),
    VERY_ACTIVE(1.725),
    EXTRA_ACTIVE(1.9)
}

enum class Gender(val lvl: Int) {
    MALE(1),
    FEMALE(2)
}

object StatusCalculator{

    fun calculateCalories(gender: Gender,activityLevel: ActivityLevel, weight: Double
                          , height: Int, age: Int) : Int {
        val calories = when (gender) {
            Gender.MALE -> {
                (WEIGHT_COST * weight + HEIGHT_COST * height + AGE_COST * age + MALE_FACTOR) * activityLevel.lvl
            }
            Gender.FEMALE -> {
                (WEIGHT_COST * weight + HEIGHT_COST * height + AGE_COST * age - FEMALE_FACTOR) * activityLevel.lvl
            }
        }
        return calories.roundToInt()
    }
}