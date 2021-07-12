package com.example.nutritiontrack.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.nutritiontrack.domain.Meal

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

@BindingAdapter("mealTotalFat")
fun TextView.setMealTotalFat(item: Meal?){
    item?.let {
        text = item.totalFat.toString()
    }
}

@BindingAdapter("mealSuger")
fun TextView.setMealSuger(item:Meal?){
    item?.let {
        text = item.suger.toString()
    }
}

@BindingAdapter("mealSodium")
fun TextView.setMealSodium(item:Meal?){
    item?.let {
        text = item.sodium.toString()
    }
}
@BindingAdapter("mealProtein")
fun TextView.setMealProtein(item:Meal?){
    item?.let{
        text = item.protein.toString()
    }
}
@BindingAdapter("mealSateratedFat")
fun TextView.setMealSateratedFat(item:Meal?){
    item?.let {
        text = item.sateratedFat.toString()
    }
}

