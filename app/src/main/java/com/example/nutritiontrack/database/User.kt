package com.example.nutritiontrack.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nutritiontrack.util.ActivityLevel
import com.example.nutritiontrack.util.Gender

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = "firstName")
    val firstName: String,
    @ColumnInfo(name = "lastName")
    val lastName: String,
    @ColumnInfo(name = "gender")
    val gender: Gender,
    @ColumnInfo(name = "activityLevel")
    val activityLevel: ActivityLevel,
    @ColumnInfo(name = "weight")
    val weight: Int,
    @ColumnInfo(name = "height")
    val height: Int,
    @ColumnInfo(name = "age")
    val age: Int
)
