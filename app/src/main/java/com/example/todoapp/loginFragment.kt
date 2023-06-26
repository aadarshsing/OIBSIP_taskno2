package com.example.todoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.example.todoapp.database.todo.personalInfo
import com.example.todoapp.databinding.FragmentLoginBinding
import com.example.todoapp.model.todoViewModelFactory
import com.example.todoapp.model.todoViewmodel

class loginFragment : Fragment() {
    private lateinit var binding :FragmentLoginBinding
    private val viewmodel: todoViewmodel by activityViewModels {
        todoViewModelFactory((activity?.application as todoApplication).database.userDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
           login()
        }

    }


    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding.editTextTextPassword2.isErrorEnabled=true
            binding.editTextTextPassword2.error = getString(R.string.enter_correct_password)
        } else {
            binding.editTextTextPassword2.isErrorEnabled = false
        }
    }
    private fun isEntryValid():Boolean{
        return viewmodel.checkforUsernamePassword(
            binding.username.text.toString(),
            binding.password.text.toString()
        )

    }
    private fun login(){
        if(isEntryValid()) {
            setErrorTextField(false)
            val action = loginFragmentDirections.actionLoginFragmentToListTask()
            findNavController().navigate(action)
        }
        else{
            setErrorTextField(true)
        }

    }
}