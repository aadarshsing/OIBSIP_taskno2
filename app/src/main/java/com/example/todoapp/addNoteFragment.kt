package com.example.todoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.database.todo.task
import com.example.todoapp.databinding.FragmentAddNoteBinding
import com.example.todoapp.model.todoViewModelFactory
import com.example.todoapp.model.todoViewmodel

class addNoteFragment : Fragment() {
    lateinit var task:task
    private val navigationArgs:noteFragmentArgs by navArgs()
    private val viewmodel: todoViewmodel by activityViewModels {
        todoViewModelFactory((activity?.application as todoApplication).database.userDao())
    }
    private lateinit var  binding:FragmentAddNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentAddNoteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    private fun addnote(){
        viewmodel.updateItem(
            binding.note.text.toString(),
            this.navigationArgs.id
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.setOnClickListener {
            bind()
        }
    }
    fun bind (){
        addnote()
        this.findNavController().navigate(addNoteFragmentDirections.actionAddNoteFragmentToListTask())

    }


}