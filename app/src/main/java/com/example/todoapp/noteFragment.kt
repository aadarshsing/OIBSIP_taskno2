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
import com.example.todoapp.databinding.FragmentNoteBinding
import com.example.todoapp.model.todoViewModelFactory
import com.example.todoapp.model.todoViewmodel

class noteFragment : Fragment() {
    private lateinit var binding:FragmentNoteBinding
    private val navigationArgs:noteFragmentArgs by navArgs()
    lateinit var task: task
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
        binding = FragmentNoteBinding.inflate(layoutInflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        viewmodel.getNote(id).observe(this.viewLifecycleOwner){
            selectedItem->task=selectedItem
            binding.note.text=task.note
        }
        binding.floatingAdd.setOnClickListener{
            findNavController().navigate(noteFragmentDirections.actionNoteFragmentToAddNoteFragment(navigationArgs.id))
        }
    }


}