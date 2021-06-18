package com.san.juan.app.blogapp.domain.auth

import com.google.firebase.auth.FirebaseUser
import com.san.juan.app.blogapp.data.remote.auth.LoginDataSource

/**
 * @author Dario Carrizo on 18/06/2021
 **/
class LoginRepoImpl(private val dataSource:LoginDataSource) : LoginRepo {

    override suspend fun signIn(email: String, password: String): FirebaseUser? = dataSource.signIn(email, password)
}