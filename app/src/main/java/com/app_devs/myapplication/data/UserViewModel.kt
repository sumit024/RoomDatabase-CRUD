package com.app_devs.myapplication.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application)
{

    private val readAllData:LiveData<List<User>>
    private val repository:Repository

    init {
        val userDao= UserDatabase.getDataBase(application).userDao()
        repository=Repository(userDao)
        readAllData=repository.readAllData

    }

    fun addUser(user:User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

}
