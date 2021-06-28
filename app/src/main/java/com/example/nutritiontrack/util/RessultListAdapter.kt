package com.example.nutritiontrack.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nutritiontrack.database.Meal
import com.example.nutritiontrack.databinding.ItemViewBinding

class RessultListAdapter :
    ListAdapter<Meal, RessultListAdapter.ResultViewHolder>(MealDiffCallback) {

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder.from(parent)
    }

    class ResultViewHolder private constructor(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Meal) {
            binding.resultMeal = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ResultViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemViewBinding.inflate(layoutInflater, parent, false)
                return ResultViewHolder(binding)
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