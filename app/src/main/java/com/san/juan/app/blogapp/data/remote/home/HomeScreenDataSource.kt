package com.san.juan.app.blogapp.data.remote.home

import com.google.firebase.firestore.FirebaseFirestore
import com.san.juan.app.blogapp.core.Resource
import com.san.juan.app.blogapp.data.model.Post
import kotlinx.coroutines.tasks.await

/**
 * @author Dario Carrizo on 8/2/2021
 **/
class HomeScreenDataSource {

    suspend fun getLatestPosts(): Resource<List<Post>>{
        val postList = mutableListOf<Post>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("posts").get().await()
        for( post in querySnapshot.documents){
            post.toObject(Post::class.java)?.let {
                postList.add(it)
            }

        }
        return Resource.Success(postList)
    }
}