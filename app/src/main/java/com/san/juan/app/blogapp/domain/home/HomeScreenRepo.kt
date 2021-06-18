package com.san.juan.app.blogapp.domain.home

import com.san.juan.app.blogapp.core.Resource
import com.san.juan.app.blogapp.data.model.Post

/**
 * @author Dario Carrizo on 8/2/2021
 **/
interface HomeScreenRepo {
    suspend fun getLatestPost(): Resource<List<Post>>
}