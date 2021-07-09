package com.example.nutritiontrack.network

import android.util.Log
import com.example.nutritiontrack.authentication.getAuthenticationInstance
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

@Volatile
var database: FirebaseFirestore = FirebaseFirestore.getInstance()

//TODO get meals from database on search
//TODO add users to database on Signup
//TODO retrive user information from the database on sign in


fun getMeals(): List<NetworkMeal>? {
    val list: MutableList<NetworkMeal>? = null
    if (getAuthenticationInstance() != null) {
        database.collection("meals")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val networkMeal: NetworkMeal = NetworkMeal(
                        Integer.parseInt(document.id),
                        document.get("name").toString(),
                        document.get("nutrition") as List<Double>
                    )
                    //Log.d("data", "${document.id} => ${document.data}")
                    list?.add(networkMeal)
                }
            }
            .addOnFailureListener {
                Log.d("data", "nope not today")
            }
    }
    return list
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



