package com.app_devs.myapplication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.app_devs.myapplication.data.Repository
import com.app_devs.myapplication.data.UserDatabase
import com.app_devs.myapplication.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application)
{

   val readAllData:LiveData<List<User>>
    private val repository: Repository

    init {
        val userDao= UserDatabase.getDataBase(application).userDao()
        repository= Repository(userDao)
        readAllData=repository.readAllData

    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

}
