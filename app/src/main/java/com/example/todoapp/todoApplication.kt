package com.example.todoapp

import android.app.Application
import com.example.todoapp.database.todo.userDatabase

class todoApplication:Application() {
    val database:userDatabase by lazy { userDatabase.getDatabase(this) }
}