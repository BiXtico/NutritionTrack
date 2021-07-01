package com.example.nutritiontrack.authentication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

private var auth: FirebaseAuth = FirebaseAuth.getInstance()

private val _authUser = MutableLiveData<FirebaseUser?>()
val authUser: LiveData<FirebaseUser?>
    get() = _authUser

fun getAuthenticationInstance(): FirebaseUser? = auth.currentUser

fun createAccount(email: String, password: String) {
    if (auth.currentUser == null) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Firebase Authentication", "createUserWithEmail:success")
                    _authUser.value = auth.currentUser
                } else {
                    Log.w("Firebase Authentication", "createUserWithEmail:failure", task.exception)
                }
            }
    }
}

fun signIn(email: String, password: String) {
    if (auth.currentUser == null) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Firebase Authentication", "createUserWithEmail:success")
                    _authUser.value = auth.currentUser
                } else {
                    Log.w(
                        "Firebase Authentication",
                        "createUserWithEmail:failure",
                        task.exception
                    )
                }
            }

    }
}