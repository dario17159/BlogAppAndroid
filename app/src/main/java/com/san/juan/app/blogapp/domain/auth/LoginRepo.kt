package com.san.juan.app.blogapp.domain.auth

import com.google.firebase.auth.FirebaseUser

/**
 * @author Dario Carrizo on 18/06/2021
 **/
interface LoginRepo {
    suspend fun signIn(email: String, password: String): FirebaseUser?
}