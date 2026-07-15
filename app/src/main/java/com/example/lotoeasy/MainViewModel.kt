package com.example.lotoeasy

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lotoeasy.db.fb.FBDatabase
import com.example.lotoeasy.db.fb.FBUser
import com.example.lotoeasy.db.fb.toFBUser
import com.example.lotoeasy.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class MainViewModel(private val db: FBDatabase) : ViewModel(), FBDatabase.Listener {

    private val _user = mutableStateOf<User?>(null)
    val user: User? get() = _user.value

    init {
        db.setListener(this)
    }

    fun register(name: String, email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    try {
                        db.register(User(name, email).toFBUser())
                        onResult(true, null)
                    } catch (e: Exception) {
                        onResult(false, e.localizedMessage)
                    }
                } else {
                    onResult(false, task.exception?.localizedMessage)
                }
            }
    }

    override fun onUserLoaded(user: FBUser) {
        _user.value = user.toUser()
    }

    override fun onUserSignOut() {
        _user.value = null
    }
}

class MainViewModelFactory(private val db: FBDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}