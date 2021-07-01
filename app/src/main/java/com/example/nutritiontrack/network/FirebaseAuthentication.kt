package com.example.nutritiontrack.authentication

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@Volatile
private var auth: FirebaseAuth = FirebaseAuth.getInstance()

fun getAuthenticationInstance(): FirebaseUser? = auth.currentUser

 fun createAccount(email: String, password: String){
    if (auth.currentUser == null) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Firebase Authentication", "createUserWithEmail:success")
                } else {
                    Log.w("Firebase Authentication", "createUserWithEmail:failure", task.exception)
                }
            }
    }else{
        Log.d("Firebase Authentication", "trying to create account while signed in")
    }
}

fun signIn(email: String, password: String){
    if (auth.currentUser == null) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Firebase Authentication", "createUserWithEmail:success")
                } else {
                    Log.w("Firebase Authentication", "createUserWithEmail:failure", task.exception)
                }
            }
    }else{
        Log.d("Firebase Authentication", "trying to create account while signed in")
    }
}