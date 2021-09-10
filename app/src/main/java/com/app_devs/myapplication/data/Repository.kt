package com.app_devs.myapplication.data

import androidx.lifecycle.LiveData
import com.app_devs.myapplication.model.User

class Repository(private val userDAO: UserDAO) {

    val readAllData:LiveData<List<User>> = userDAO.readAllData()
    suspend fun addUser(user: User){
        userDAO.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDAO.updateUser(user)
    }

}