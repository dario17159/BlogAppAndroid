package com.san.juan.app.blogapp.domain.home

import com.san.juan.app.blogapp.core.Resource
import com.san.juan.app.blogapp.data.model.Post
import com.san.juan.app.blogapp.data.remote.home.HomeScreenDataSource

/**
 * @author Dario Carrizo on 8/2/2021
 **/
class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource): HomeScreenRepo {

    override suspend fun getLatestPost(): Resource<List<Post>> = dataSource.getLatestPosts()
}