package com.example.nutritiontrack.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nutritiontrack.database.Meal
import com.example.nutritiontrack.databinding.HomeMealBinding

class MealListAdapter(private val clickListener: MealClickListener): ListAdapter<Meal, MealListAdapter.HomeMealViewHolder>(MealDiffCallback) {

    override fun onBindViewHolder(holder: HomeMealViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(getItem(position)!!,clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMealViewHolder {
        return HomeMealViewHolder.from(parent)
    }

    class HomeMealViewHolder private constructor(private val binding: HomeMealBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Meal, clickListener: MealClickListener) {
            binding.meal = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): HomeMealViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HomeMealBinding.inflate(layoutInflater, parent, false)
                return HomeMealViewHolder(binding)
            }
        }
    }
}

object MealDiffCallback : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.mealId == newItem.mealId
    }
}
class MealClickListener(val clickListener:(mealId:Long) -> Unit){
    fun onClick(meal : Meal) = clickListener(meal.mealId)
}