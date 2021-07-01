package com.example.nutritiontrack.authentication

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


private var auth: FirebaseAuth = FirebaseAuth.getInstance()

fun getAuthenticationInstance(): FirebaseUser? = auth.currentUser

fun createAccount(email: String, password: String): FirebaseUser? {
    if (auth.currentUser == null) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Firebase Authentication", "createUserWithEmail:success")
                } else {
                    Log.w("Firebase Authentication", "createUserWithEmail:failure", task.exception)
                }
            }
        return auth.currentUser
    }else{
        Log.d("Firebase Authentication", "trying to create account while signed in")
        return auth.currentUser
    }
}

fun signIn(email: String, password: String): Boolean {
    var value = false
   if (auth.currentUser == null) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Firebase Authentication", "createUserWithEmail:success")
                    value = true
                } else {
                    Log.w("Firebase Authentication", "createUserWithEmail:failure", task.exception)
                }
            }
    }
    return value
}