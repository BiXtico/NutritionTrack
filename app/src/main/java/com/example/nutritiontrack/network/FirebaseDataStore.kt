package com.example.nutritiontrack.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nutritiontrack.authentication.authUser
import com.example.nutritiontrack.authentication.getAuthenticationInstance
import com.example.nutritiontrack.database.DAO
import com.example.nutritiontrack.database.DatabaseMeal
import com.example.nutritiontrack.database.DatabaseUser
import com.example.nutritiontrack.domain.Meal

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.withContext


private lateinit var instance: FirebaseDataStore

fun getFireStore(): FirebaseDataStore {
    if (!::instance.isInitialized) {
        instance = FirebaseDataStore()
    }
    return instance
}

class FirebaseDataStore() {
    @Volatile
    private var database: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _mealList = MutableLiveData<List<Meal>>()
    val meaList: LiveData<List<Meal>>
        get() = _mealList

//TODO get meals from database on search
//TODO add users to database on Signup
//TODO retrive user information from the database on sign in


    fun getMeals() {
        if (getAuthenticationInstance() != null) {
            val list: MutableList<NetworkMeal> = mutableListOf()
            database.collection("meals")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val networkMeal = NetworkMeal(
                            Integer.parseInt(document.id),
                            document.get("name").toString(),
                            document.get("nutrition") as List<Double>
                        )
                        Log.d(
                            "data",
                            "${networkMeal.name} => ${networkMeal.mealId} => ${networkMeal.nutrition[0]}"
                        )
                        list.add(networkMeal)
                    }
                    _mealList.value = list.mealAsDomainModel()
                }
                .addOnFailureListener {
                    Log.d("data", "nope not today")
                }
        }
    }

    fun getMeal(searchable: String): List<NetworkMeal>? {
        val list: MutableList<NetworkMeal>? = null
        if (getAuthenticationInstance() != null) {
            database.collection("meals")
                .whereEqualTo("name", "$searchable")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val networkMeal: NetworkMeal = NetworkMeal(
                            Integer.parseInt(document.id),
                            document.get("name").toString(),
                            document.get("nutrition") as List<Double>
                        )
                        Log.d("data", "${document.id} => ${document.data}")
                        Log.d(
                            "data",
                            "${networkMeal.mealId} that has name ${networkMeal.name} with array ${networkMeal.nutrition}"
                        )
                        list?.add(networkMeal)
                    }
                }
                .addOnFailureListener {
                    Log.d("data", "nope not today")
                }
        }
        return list
    }
}




