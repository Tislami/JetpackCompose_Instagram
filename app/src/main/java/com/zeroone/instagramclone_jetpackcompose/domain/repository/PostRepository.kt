package com.zeroone.instagramclone_jetpackcompose.domain.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.zeroone.instagramclone_jetpackcompose.domain.model.Post
import com.zeroone.instagramclone_jetpackcompose.domain.model.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.io.InputStream
import java.util.*
import kotlin.reflect.jvm.internal.impl.util.AbstractArrayMapOwner

interface PostRepository {
    fun setPost(post: Post) : Flow<Response<String>>
    fun setPostPhoto(inputStream: InputStream,owner: String) : Flow<Response<String>>
    fun getUserPosts(id: String): Flow<Response<List<Post>>>
    fun getPosts() : Flow<Response<List<Post>>>
}

class PostRepositoryImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val userCollection: CollectionReference,
    private val postCollection: CollectionReference,
    private val postStorageReference: StorageReference
) : PostRepository {

    override fun setPost(post: Post) = flow {
        try {
            Log.d("PostApp", "post_repo_setPost: init")
            var result: Response<String> = Response.Loading
            emit(result)

            val id = postCollection.document().id
            val newPost= post.copy(id= id, owner = auth.currentUser!!.uid)

            val userCollection = userCollection.document(auth.currentUser!!.uid)
            val postCollection = postCollection.document(id)

            firestore.runBatch {batch ->
                batch.set(postCollection, newPost)
                batch.update(userCollection,"posts",  FieldValue.arrayUnion(id))
            }.addOnSuccessListener {
                Log.d("PostApp", "post_repo_setPost: success $id")
                result = Response.Success(id)
            }.addOnFailureListener {
                Log.d("PostApp", "post_repo_setPost: failure ${it.message}")
                result = Response.Error(it.message ?: "Unknown error")
            }.await()
            emit(result)
        } catch (e: Exception) {
            Log.d("PostApp", "post_repo_setPost: success ${e.message}")
            emit(Response.Error(e.message ?: "Unknown error"))
        }
    }

    override fun setPostPhoto(
        inputStream: InputStream,
        owner: String
    ) = flow {
        try {
            Log.d("PostApp", "post_repo_setPhoto: init")
            var result: Response<String> = Response.Loading
            emit(result)
            val reference = postStorageReference
                .child(owner)
                .child(UUID.randomUUID().leastSignificantBits.toString())
            val url = reference.putStream(inputStream).continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        Log.d("PostApp", "post_repo_setPhoto: task fail ${it.message}")
                        result = Response.Error(it.message ?: "Error")
                    }
                }
                Log.d("PostApp", "post_repo_setPhoto: success ${reference.downloadUrl}")
                reference.downloadUrl
            }.await()
            Log.d("PostApp", "post_repo_setPhoto: done")
            result = Response.Success(url.toString())
            emit(result)
        } catch (e: Exception) {
            Log.d("PostApp", "post_repo_setPhoto: error ${e.message}")
            emit(Response.Error(e.message ?: "Error"))
        }
    }

    override fun getUserPosts(id: String) = callbackFlow {
        Log.d("PostApp", "post_repo_getUserPosts: init")
        trySend(Response.Loading)
        val snapshot = postCollection.whereEqualTo("owner", id)
            .addSnapshotListener { value, error ->
                val response = if (value != null) {
                    val post = value.toObjects(Post::class.java)
                    Log.d("PostApp", "post_repo_getUserPosts: success")
                    Response.Success(post)
                } else {
                    Log.d("PostApp", "post_repo_getUserPosts: error ${error?.message}")
                    Response.Error(error?.message ?: "")
                }
                Log.d("PostApp", "post_repo_getUserPosts: done")
                trySend(response)
            }
        awaitClose {
            snapshot.remove()
        }
    }

    override fun getPosts() = callbackFlow {
        Log.d("discoveryApp", "post_repo_getPosts: init")

        val snapshot = postCollection.addSnapshotListener { value, error ->
            val response = if (value != null) {
                val posts = value.toObjects(Post::class.java)
                Log.d("discoveryApp", "post_repo_getPosts: success ${posts.size}")
                Response.Success(posts)
            } else {
                Log.d("discoveryApp", "post_repo_getPosts: error ${error?.message}")
                Response.Error(error?.message ?: "")
            }
            trySend(response)
        }
        awaitClose {
            snapshot.remove()
        }
    }
}