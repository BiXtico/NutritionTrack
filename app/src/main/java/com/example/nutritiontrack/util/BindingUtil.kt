package com.example.nutritiontrack.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.nutritiontrack.database.Meal

@BindingAdapter("mealName")
fun TextView.setMealName(item: Meal?){
    item?.let {
        text = item.name
    }
}

@BindingAdapter("mealCalories")
fun TextView.setMealCalories(item: Meal?){
    item?.let {
        text = item.calories.toString()
    }
}

@BindingAdapter("mealAmount")
fun TextView.setMealAmount(item: Meal?){
    item?.let {
        text = item.amount.toString()
    }
}