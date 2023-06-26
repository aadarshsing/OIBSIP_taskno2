package com.example.todoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.todoapp.database.todo.personalInfo
import com.example.todoapp.databinding.FragmentSignupBinding
import com.example.todoapp.model.todoViewModelFactory
import com.example.todoapp.model.todoViewmodel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class signupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private val viewmodel: todoViewmodel by activityViewModels {
        todoViewModelFactory((activity?.application as todoApplication).database.userDao())
    }
    private  var item:Boolean?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signup.setOnClickListener {
            signup()
        }
    }

    private  fun isusernameCorrect(): Boolean{
        val item=binding.username.text.toString()
        if(viewmodel.checkforUsername(item)){
            return false}
        else
            return true
    }
    private fun isPasswordCorrect():Boolean{
       return binding.password.text.toString().equals(binding.confirmpassword.text.toString())
    }
    private fun signup(){
        if (isEntryValid()) {
            setErrorTextFieldForBlank(false)
            if (isusernameCorrect()) {

                setErrorTextField(false)
                if (isPasswordCorrect()) {
                    setErrorTextFieldforPassword(false)
                    viewmodel.newLogin(
                        binding.username.text.toString(),
                        binding.password.text.toString()
                    )
                    findNavController().navigate(signupFragmentDirections.actionSignupFragmentToLoginFragment())
                }
                setErrorTextFieldforPassword(true)
            } else {
                setErrorTextField(true)
            }
        }
        else
            setErrorTextFieldForBlank(true)
    }
    private fun isEntryValid():Boolean{
        return viewmodel.isEntryValid(
            binding.username.text.toString(),
            binding.password.text.toString()
        )
    }

    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding.editTextTextEmailAddress2.isErrorEnabled=true
            binding.editTextTextEmailAddress2.error = getString(R.string.enter_other_username)
        } else {
            binding.editTextTextEmailAddress2.isErrorEnabled = false
        }
    }
    private fun setErrorTextFieldForBlank(error: Boolean) {
        if (error) {
            binding.editTextTextEmailAddress2.isErrorEnabled=true
            binding.editTextTextEmailAddress2.error = getString(R.string.enter_valid_username)
            binding.editTextTextPassword.isErrorEnabled=true
            binding.editTextTextPassword.error=getString(R.string.enter_valid_username)
        } else {
            binding.editTextTextEmailAddress2.isErrorEnabled = false
        }
    }
    private fun setErrorTextFieldforPassword(error: Boolean) {
        if (error) {
            binding.editTextTextPassword3.isErrorEnabled=true
            binding.editTextTextPassword3.error = getString(R.string.write_matching_password)
        } else {
            binding.editTextTextPassword3.isErrorEnabled = false
        }
    }

}