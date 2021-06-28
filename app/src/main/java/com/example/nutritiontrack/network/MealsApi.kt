package com.example.nutritiontrack.network
import com.example.nutritiontrack.database.Meal
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val baseURL = "https://bixtico.github.io/"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(baseURL).build()

interface MealAPI {
    @GET("Data/RAW_recipes.json")
    fun getMeals() :
            Call<String>
}

object API {
    val retrofitService :MealAPI by lazy {
        retrofit.create(MealAPI::class.java)
    }
}