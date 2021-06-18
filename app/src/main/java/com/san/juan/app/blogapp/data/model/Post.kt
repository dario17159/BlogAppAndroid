package com.san.juan.app.blogapp.data.model

import com.google.firebase.Timestamp

/**
 * @author Dario Carrizo on 8/2/2021
 **/
data class Post(
    val profile_picture: String = "",
    val profile_name: String = "",
    val post_timestamp: Timestamp? = null,
    val post_image: String = ""
)
