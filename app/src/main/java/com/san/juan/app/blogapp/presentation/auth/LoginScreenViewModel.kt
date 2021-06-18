package com.san.juan.app.blogapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.san.juan.app.blogapp.core.Resource
import com.san.juan.app.blogapp.domain.auth.LoginRepo
import kotlinx.coroutines.Dispatchers

/**
 * @author Dario Carrizo on 18/06/2021
 **/
class LoginScreenViewModel(private val repo: LoginRepo): ViewModel() {

    fun signIn(email: String, password: String) = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.signIn(email, password)))
        }catch (ex: Exception){
            emit(Resource.Failure(ex))
        }
    }
}

class LoginScreenViewModelFactory(private val repo: LoginRepo): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginScreenViewModel(repo) as T
    }
}
