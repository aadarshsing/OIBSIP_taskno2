package com.example.todoapp.database.todo

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [personalInfo::class,task::class], version = 2, exportSchema = false)
abstract class userDatabase:RoomDatabase() {
    abstract fun userDao():UserDao
    companion object{
        @Volatile
        private var INSTANCE:userDatabase?=null
        fun getDatabase(context: Context):userDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    userDatabase::class.java,
                    "user_database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                INSTANCE=instance
                return instance
            }
        }



    }
}