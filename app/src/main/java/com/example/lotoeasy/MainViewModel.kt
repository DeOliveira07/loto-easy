package com.example.lotoeasy

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lotoeasy.api.LotomaniaResponse
import com.example.lotoeasy.api.RetrofitClient
import com.example.lotoeasy.db.fb.FBDatabase
import com.example.lotoeasy.db.fb.FBUser
import com.example.lotoeasy.db.fb.toFBUser
import com.example.lotoeasy.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val db: FBDatabase) : ViewModel(), FBDatabase.Listener {

    private val _user = mutableStateOf<User?>(null)
    val user: User? get() = _user.value

    // Estado da Lotomania (guarda o último concurso baixado)
    private val _lotomaniaState = mutableStateOf<LotomaniaResponse?>(null)
    val lotomaniaState: LotomaniaResponse? get() = _lotomaniaState.value

    // Estado de carregamento da API
    private val _isLoadingLotomania = mutableStateOf(false)
    val isLoadingLotomania: Boolean get() = _isLoadingLotomania.value

    init {
        db.setListener(this)
        fetchLotomania() // Busca os dados da Lotomania ao iniciar
    }

    fun fetchLotomania() {
        _isLoadingLotomania.value = true
        RetrofitClient.lotomaniaApi.getUltimoConcurso().enqueue(object : Callback<LotomaniaResponse> {
            override fun onResponse(call: Call<LotomaniaResponse>, response: Response<LotomaniaResponse>) {
                _isLoadingLotomania.value = false
                if (response.isSuccessful) {
                    _lotomaniaState.value = response.body()
                }
            }

            override fun onFailure(call: Call<LotomaniaResponse>, t: Throwable) {
                _isLoadingLotomania.value = false
                // Tratamento de falha de conexão silenciada ou logada
            }
        })
    }

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    onResult(false, task.exception?.localizedMessage)
                }
            }
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