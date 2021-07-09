package com.example.nutritiontrack.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nutritiontrack.domain.Meal
import com.example.nutritiontrack.domain.User
import com.example.nutritiontrack.util.ActivityLevel
import com.example.nutritiontrack.util.Gender

@Entity(tableName = "meal_table")
data class DatabaseMeal(
    @PrimaryKey(autoGenerate = true)
    var mealId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "calories")
    val calories: Double,
    @ColumnInfo(name = "amount")
    val amount: Double,
    @ColumnInfo(name = "totalFat")
    val totalFat: Double,
    @ColumnInfo(name = "suger")
    val suger: Double,
    @ColumnInfo(name = "sodium")
    val sodium: Double,
    @ColumnInfo(name = "protein")
    val protein: Double,
    @ColumnInfo(name = "sateratedFat")
    val sateratedFat: Double
)
@Entity(tableName = "user_table")
data class DatabaseUser(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
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

fun List<DatabaseMeal>.mealsAsDomainModel():List<Meal>{
    return map {
        Meal(
            mealId = it.mealId,
            name = it.name,
            calories = it.calories,
            amount = it.amount,
            totalFat = it.totalFat,
            suger = it.suger,
            sodium = it.sodium,
            protein = it.protein,
            sateratedFat = it.sateratedFat
        )
    }
}

fun List<DatabaseUser>.usersAsDomainModel():List<User>{
    return map {
        User(
            id = it.id,
            firstName = it.firstName,
            lastName = it.lastName,
            gender = it.gender,
            activityLevel = it.activityLevel,
            weight = it.weight,
            height = it.height,
            age = it.age
        )
    }
}