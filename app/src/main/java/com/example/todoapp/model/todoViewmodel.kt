package com.example.todoapp.model
import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import androidx.lifecycle.viewModelScope
import com.example.todoapp.database.todo.UserDao
import com.example.todoapp.database.todo.personalInfo
import com.example.todoapp.database.todo.task
import kotlinx.coroutines.*
import java.nio.file.Files.copy
import java.util.Date

class todoViewmodel(private val userDao: UserDao):ViewModel() {
    private fun insert(personalInfo: personalInfo){
        viewModelScope.launch {
            userDao.insert(personalInfo)
        }
    }
    private fun getNewLogin(username:String,password:String):personalInfo
    {
        return personalInfo(username = username,
                            password = password)
    }
    fun newLogin(username: String,password: String){
        val newLogin=getNewLogin(username,password)
        insert(newLogin)
    }
    fun checkforUsername(username: String) :Boolean{
        return userDao.isTaken(username)

    }
    fun checkforUsernamePassword(username: String,password: String):Boolean{
        return userDao.login(username, password)

    }
    fun isEntryValid(username: String,password: String):Boolean{
        if(username.isBlank() || password.isBlank()){
            return false
        }
        else
            return true
    }
    private fun insertForTask(task: task){
        viewModelScope.launch {
            userDao.inserfForTask(task)
        }
    }
    fun isvalidEntry(event:String,date: String):Boolean{
        if(event.isBlank()|| date.toString().isBlank()){
            return false
        }
        return true

    }
    private fun getNewItemEntry(event: String,date: String):task{
        return task(
            event = event,
            date = date,
            note=" ")
    }
    fun newTaskEntry(event: String,date: String){
        val newItem =getNewItemEntry(event,date)
        insertForTask(newItem)
    }
    val getAllItems:LiveData<List<task>> = userDao.getAll().asLiveData()

    fun getNote(id:Int):LiveData<task>{
        return userDao.getNote(id).asLiveData()
    }
    fun updateItem(note:String,id: Int){
        viewModelScope.launch {
            userDao.updateNote(note,id)
        }
    }
    fun deleteForTask(task: task){
        viewModelScope.launch{
            userDao.deleteForTask(task)
        }
    }

}
class todoViewModelFactory(private val userDao: UserDao):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(todoViewmodel::class.java)){
            @Suppress
            return todoViewmodel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}