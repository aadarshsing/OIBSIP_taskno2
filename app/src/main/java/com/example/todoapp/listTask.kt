package com.example.todoapp

import android.icu.text.Transliterator.Position
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.database.todo.task
import com.example.todoapp.databinding.FragmentListTaskBinding
import com.example.todoapp.model.todoAdapter
import com.example.todoapp.model.todoViewModelFactory
import com.example.todoapp.model.todoViewmodel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class listTask : Fragment(),todoAdapter.noteClickDeleteInterface {

    private lateinit var binding: FragmentListTaskBinding
    private val viewmodel: todoViewmodel by activityViewModels {
        todoViewModelFactory((activity?.application as todoApplication).database.userDao())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentListTaskBinding.inflate(inflater,container,false)
        return binding.root
    }
    private fun showConfirmationDialog(task: task) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage("Are you sure want to delete?")
            .setCancelable(false)
            .setPositiveButton("yes") { _, _ ->
                deleteItem(task)
            }
            .setNegativeButton("no") { _, _ -> }
            .show()
    }

    private fun deleteItem(task: task) {
        return viewmodel.deleteForTask(task)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter=todoAdapter(this)
        viewmodel.getAllItems.observe(this.viewLifecycleOwner){
            items->items.let {
                adapter.submitList(it)


        }


        }
        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager=LinearLayoutManager(this.context)
        binding.floatingAdd.setOnClickListener{
            findNavController().navigate(listTaskDirections.actionListTaskToAddEventFragment())
        }
    }

    override fun onDeleteIconClick(task: task) {
        showConfirmationDialog(task)
    }

    override fun itemViewClick(task: task) {
        val adapter =todoAdapter
        findNavController().navigate(listTaskDirections.actionListTaskToNoteFragment(id = task.id))
    }


}