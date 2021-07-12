package com.example.nutritiontrack.ui.meals

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nutritiontrack.domain.Meal
import com.example.nutritiontrack.network.FirebaseDataStore
import com.example.nutritiontrack.network.getFireStore

class MealViewModel : ViewModel() {

    private val database: FirebaseDataStore = getFireStore()

    var displayMeal: Meal? = null

    fun assignMeal(mealId: Int): Meal? {
        database.meaList.value?.forEach { meal: Meal ->
            if (meal.mealId == mealId)
                this.displayMeal = meal
        }
        return null
    }


}