package com.example.todoapp.database.todo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "personalInfo")
 data class personalInfo(
    @PrimaryKey(autoGenerate = true)
    val id : Int=0,
    @ColumnInfo
    val username : String,
    @ColumnInfo
    val password : String,

 )
@Entity(tableName = "task")
data class task(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    @ColumnInfo(name = "event")
    val event : String,
    @ColumnInfo(name = "date")
    val date :String,
    @ColumnInfo(name = "note")
    val note : String
)