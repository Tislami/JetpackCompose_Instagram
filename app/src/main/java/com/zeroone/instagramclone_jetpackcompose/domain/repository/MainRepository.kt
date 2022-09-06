package com.zeroone.instagramclone_jetpackcompose.domain.repository

import android.util.Log
import com.zeroone.instagramclone_jetpackcompose.data.FirebaseDatabase
import com.zeroone.instagramclone_jetpackcompose.domain.model.Response
import com.zeroone.instagramclone_jetpackcompose.domain.model.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

interface MainRepository {
    fun searchUser(query: String) : Flow<Response<List<User>>>
}

class MainRepositoryImpl(
    private val firebase: FirebaseDatabase
) : MainRepository{

    override fun searchUser(query: String) = callbackFlow {
        Log.d("discoveryApp", "main_repo_search: init")
        val snapshot = firebase.userCollection
            .addSnapshotListener { value, error ->
                val response = if (value!=null){
                    val users = mutableListOf<User>()
                    value.documents.forEach {document ->
                        val name :String = document.data?.get("name") as String
                        if (name.lowercase().contains(query.lowercase())){
                            users.add(document.toObject(User::class.java)!!)
                        }
                    }
                    Log.d("discoveryApp", "main_repo_search: success ${users.size}")

                    Response.Success(users)
                }else{
                    Log.d("discoveryApp", "main_repo_search: error ${error?.message}")
                    Response.Error(error?.message?:"")
                }
                trySend(response)
            }
        awaitClose { snapshot.remove() }
    }

}