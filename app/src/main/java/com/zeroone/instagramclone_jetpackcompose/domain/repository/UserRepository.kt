package com.zeroone.instagramclone_jetpackcompose.domain.repository


import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.zeroone.instagramclone_jetpackcompose.data.FirebaseDatabase
import com.zeroone.instagramclone_jetpackcompose.domain.model.Response
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await


interface UserRepository {
    fun getUser(id: String): Flow<Response<User?>>
    fun setUser(user: User): Flow<Response<User>>
    fun signOut(): Flow<Response<Boolean>>
}

class UserRepositoryImpl(
    private val userCollection: CollectionReference)  : UserRepository {

    override fun setUser(user: User) = flow {
        try {
            Log.d("AppAuth", "user_repo_set: init ")
            var result: Response<User> = Response.Loading
            emit(result)

            Log.d("AppAuth", "user_repo_set: creating document ${user.id} ")
            userCollection.document(user.id).set(user)
                .addOnSuccessListener {
                    Log.d("AppAuth", "user_repo_set: set user success ${user.email}")
                    result = Response.Success(user)
                }
                .addOnFailureListener {
                    Log.d("AppAuth", "user_repo_set: set user failure ${it.message} ")
                    result =  Response.Error(it.message ?: "Failure")
                }.await()
            emit(result)
        } catch (e: Exception) {
            Log.d("AppAuth", "user_repo_set: error ${e.message} ")
            emit(Response.Error(e.message ?: "Error"))
        }
    }

    override fun getUser(id: String) = callbackFlow {
        trySend(Response.Loading)
        Log.d("AppAuth", "user_repo_get: init ")
        val snapshotListener = userCollection.document(id)
            .addSnapshotListener { value, error ->
                val response= if (value != null) {
                    val user = value.toObject(User::class.java)
                    Log.d("AppAuth", "user_repo_get: get user success ${user?.id} ")
                    Response.Success(user)
                } else {
                    Log.d("AppAuth", "user_repo_get: get user failure ${error?.message} ")
                    Response.Error(error?.message ?: "Unknown error")
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun signOut() = flow {
        try {
            emit(Response.Loading)
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }
}