package com.example.lotoeasy.db.fb

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class FBDatabase {

    interface Listener {
        fun onUserLoaded(user: FBUser)
        fun onUserSignOut()
    }

    private val auth = Firebase.auth
    private val db = Firebase.firestore
    private var listener: Listener? = null


    init {
        auth.addAuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser == null) {
                listener?.onUserSignOut()
            } else {
                val uid = firebaseAuth.currentUser!!.uid
                val refCurrUser = db.collection("users").document(uid)

                refCurrUser.get().addOnSuccessListener { document ->
                    document.toObject(FBUser::class.java)?.let { fbUser ->
                        listener?.onUserLoaded(fbUser)
                    }
                }
            }
        }
    }

    fun setListener(listener: Listener?) {
        this.listener = listener
    }
    fun register(user: FBUser) {
        if (auth.currentUser == null) {
            throw RuntimeException("User not logged in!")
        }
        val uid = auth.currentUser!!.uid
        db.collection("users").document(uid).set(user)
    }
    fun salvarTalao(talao: FBTalao, onComplete: (Boolean, String?) -> Unit) {
        val currentUser = Firebase.auth.currentUser
        if (currentUser != null) {
            val ref = db.collection("users")
                .document(currentUser.uid)
                .collection("taloes")
                .document() // Gera ID automático no Firestore

            talao.id = ref.id

            ref.set(talao)
                .addOnSuccessListener { onComplete(true, null) }
                .addOnFailureListener { e -> onComplete(false, e.localizedMessage) }
        } else {
            onComplete(false, "Usuário não autenticado.")
        }
    }

    fun escutarTaloes(onUpdate: (List<FBTalao>) -> Unit) {
        val currentUser = Firebase.auth.currentUser ?: return
        db.collection("users")
            .document(currentUser.uid)
            .collection("taloes")
            .addSnapshotListener { snapshot, error ->
                if (error != null || snapshot == null) return@addSnapshotListener
                val taloes = snapshot.toObjects(FBTalao::class.java)
                onUpdate(taloes)
            }
    }
}