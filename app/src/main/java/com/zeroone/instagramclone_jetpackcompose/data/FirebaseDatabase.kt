package com.zeroone.instagramclone_jetpackcompose.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class FirebaseDatabase {
    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference

    val auth : FirebaseAuth = FirebaseAuth.getInstance()
    val userCollection: CollectionReference = firestore.collection("users")
    val postCollection: CollectionReference = firestore.collection("posts")

    val userStorageRef: StorageReference = storageRef.child("users_images")
    val postStorageRef: StorageReference = storageRef.child("post_images")


}