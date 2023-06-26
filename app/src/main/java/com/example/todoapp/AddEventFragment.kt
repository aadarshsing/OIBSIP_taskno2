package com.example.todoapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.BoringLayout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.database.todo.task
import com.example.todoapp.databinding.FragmentAddEventBinding
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.model.todoViewModelFactory
import com.example.todoapp.model.todoViewmodel
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*


class AddEventFragment : Fragment() {

  private lateinit var binding:FragmentAddEventBinding
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
        binding=FragmentAddEventBinding.inflate(inflater,container,false)
        return  binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.date.setOnClickListener {
            DatePickerDialog(requireContext(),DatePickerDialog.OnDateSetListener { view:DatePicker, year:Int, month:Int, dayOfMonth:Int ->
                binding.date.setText(" " + year + "/" +month+ "/" + dayOfMonth)
            }, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                .show()

        }
        binding.saveButton.setOnClickListener {
            UpdatenewEntry()
        }
    }
    private fun isEntryValid():Boolean{
        return viewmodel.isvalidEntry(
            binding.event.toString(),
            binding.event.toString()
        )
    }
    private fun UpdatenewEntry(){
        if(isEntryValid()){
            viewmodel.newTaskEntry(
                binding.event.text.toString(),
                binding.date.text.toString()
            )
            findNavController().navigate(AddEventFragmentDirections.actionAddEventFragmentToListTask())
        }

    }




}