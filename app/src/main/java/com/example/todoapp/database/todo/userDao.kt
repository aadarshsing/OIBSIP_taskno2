package com.example.todoapp.database.todo

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao{
    @Insert
    suspend fun insert(personalInfo:personalInfo)

    @Query("select exists ( select * from personalInfo where username =:username)")
    fun isTaken(username:String):Boolean

    @Query("SELECT EXISTS ( SELECT * FROM personalInfo WHERE username =:username and password =:password)")
     fun login(username: String,password:String):Boolean

     @Insert
     suspend fun inserfForTask(task: task)

     @Delete
     suspend fun deleteForTask(task: task)

     @Query("select * from task order by event ASC")
     fun getAll():Flow<List<task>>

     @Query("select * from task where id =:id")
     fun getNote(id:Int):Flow<task>

     @Query("update task set note =:note where id =:id")
    suspend fun updateNote(note:String,id: Int)


}