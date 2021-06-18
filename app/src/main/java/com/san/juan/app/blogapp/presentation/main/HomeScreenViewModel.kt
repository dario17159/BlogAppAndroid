package com.san.juan.app.blogapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.san.juan.app.blogapp.core.Resource
import com.san.juan.app.blogapp.domain.home.HomeScreenRepo
import kotlinx.coroutines.Dispatchers

/**
 * @author Dario Carrizo on 8/2/2021
 **/
class HomeScreenViewModel(private val repo: HomeScreenRepo): ViewModel() {

    fun fetchLatestPost() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try{
            emit(repo.getLatestPost())
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}

class HomeScreenViewModelFactory(private val repo: HomeScreenRepo): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeScreenRepo::class.java).newInstance(repo)
    }
}