package com.example.nutritiontrack.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_table")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    var mealId: Long = 0L,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "date")
    val date: Int,
    @ColumnInfo(name = "calories")
    val calories: Int,
    @ColumnInfo(name = "amount")
    val amount: Int,
    @ColumnInfo(name = "totalFat")
    val totalFat: Int,
    @ColumnInfo(name = "suger")
    val suger: Int,
    @ColumnInfo(name = "sodium")
    val sodium: Int,
    @ColumnInfo(name = "protein")
    val protein: Int,
    @ColumnInfo(name = "sateratedFat")
    val sateratedFat: Int
)