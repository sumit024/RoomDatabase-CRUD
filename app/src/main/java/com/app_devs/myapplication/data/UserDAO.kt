package com.app_devs.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app_devs.myapplication.model.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData():LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user:User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()
}