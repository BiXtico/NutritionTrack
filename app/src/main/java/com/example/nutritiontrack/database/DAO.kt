package com.example.nutritiontrack.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DAO {
    //Meal
    @Insert
    fun insert(meal: Meal)
    @Update
    fun update(meal: Meal)
    @Query("select * from meal_table where date = :key" )
    fun getMeal(key:Long): Meal
    @Query("delete from meal_table")
    fun clearMeals()
    @Query("delete from meal_table where mealId = :key")
    fun deleteMeal(key:Long)

    //User
    @Insert
    fun insert(user: User)
    @Update
    fun update(user: User)
    @Query("select * from USER_TABLE where id = :key" )
    fun getUser(key:Long): User
    @Query("delete from user_table")
    fun clearUsers()
    @Query("delete from user_table where id = :key")
    fun deleteUser(key:Long)
}
