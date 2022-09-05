package com.zeroone.instagramclone_jetpackcompose.domain.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.zeroone.instagramclone_jetpackcompose.domain.model.Response
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import com.zeroone.instagramclone_jetpackcompose.domain.model.defaultUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

interface AuthRepository {
    fun createUser(email: String, password: String): Flow<Response<String?>>
    fun login(email: String, password: String): Flow<Response<String?>>
    fun getAuthState(): Flow<Response<String?>>
}

class AuthRepositoryImpl(private val auth: FirebaseAuth) : AuthRepository {

    override fun createUser(email: String, password: String) = flow {
        try {
            Log.d("AppAuth", "auth_repo_create_user: init")
            var result: Response<String> = Response.Loading
            emit(result)
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    Log.d("AppAuth", "auth_repo_create_user: created ${it.user!!.uid}")
                    result = Response.Success(it.user!!.uid)
                }.addOnFailureListener {
                    Log.d("AppAuth", "auth_repo_create_user: created error ${it.message}")
                    result = Response.Error(it.message?: "Unknown error")
                }.await()
            Log.d("AppAuth", "auth_repo_create_user: done")

            emit(result)
        } catch (e: Exception) {
            Log.d("AppAuth", "auth_repo_create_user: error $e")
            emit(Response.Error(e.message ?: "Error"))
        }
    }

    override fun login(email: String, password: String) = flow {
        try {
            Log.d("AppAuth", "auth_repo_login: init")
            var result: Response<String?> = Response.Loading
            emit(result)
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    Log.d("AppAuth", "auth_repo_login: singed ${it.user!!.uid}")
                    result = Response.Success(it.user!!.uid) }
                .addOnFailureListener {
                    Log.d("AppAuth", "auth_repo_login: singed error ${it.message}")
                    result = Response.Error(it.message?: "Unknown error") }
                .await()
            Log.d("AppAuth", "auth_repo_login: done")

            emit(result)
        } catch (e: Exception) {
            Log.d("AppAuth", "auth_repo_login: error $e")

            emit(Response.Error(e.message ?: "Unknown error"))
        }
    }


    override fun getAuthState() = flow {
        try {
            emit(Response.Loading)
            val result = Response.Success(auth.currentUser?.uid)
            emit(result)
        }
        catch (e:Exception){
            emit(Response.Error(e.message?:"Unknown"))
        }
    }

}