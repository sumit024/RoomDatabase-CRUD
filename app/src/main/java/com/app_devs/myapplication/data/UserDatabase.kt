package com.app_devs.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app_devs.myapplication.model.User

@Database(entities = [User::class],version = 1,exportSchema = false)
abstract class UserDatabase:RoomDatabase()
{
    abstract fun userDao():UserDAO

    companion object{
        @Volatile
        private var INSTANCE:UserDatabase?=null
        fun getDataBase(context: Context):UserDatabase
        {
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance=Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                return instance

            }
        }
    }

}