package com.example.nutritiontrack.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DAO {
    //Meal
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(meal: DatabaseMeal)
    @Update
    fun update(meal: DatabaseMeal)
    @Query("select * from meal_table")
    fun getAllMeals(): LiveData<List<DatabaseMeal>>
    @Query("delete from meal_table")
    fun clearMeals()
    @Query("delete from meal_table where mealId = :key")
    fun deleteMeal(key:Long)

    //User
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: DatabaseUser)
    @Update
    fun update(user: DatabaseUser)
    @Query("select * from USER_TABLE where id = :key" )
    fun getUser(key:Long): LiveData<DatabaseUser>
    @Query("delete from user_table")
    fun clearUsers()
}
