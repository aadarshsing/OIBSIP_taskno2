package com.example.todoapp.model

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todoapp.database.todo.task
import com.example.todoapp.databinding.TaskItemBinding
import java.lang.reflect.Array.get

class todoAdapter (private val onClickEvent:noteClickDeleteInterface):ListAdapter<task,todoAdapter.todoViewHolder>(DiffCallback) {
     class todoViewHolder(val binding: TaskItemBinding):RecyclerView.ViewHolder(binding.root) {
         fun bind(task: task){
             binding.dateHolder.text=task.date.toString()
             binding.title.text=task.event
         }

     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): todoViewHolder {
        return todoViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context)))
     }

     override fun onBindViewHolder(holder: todoViewHolder, position: Int) {
        val current=getItem(position)
         holder.binding.delete.setOnClickListener {
             onClickEvent.onDeleteIconClick(current)
         }
         holder.itemView.setOnClickListener{
             onClickEvent.itemViewClick(current)

         }
         holder.bind(current)
     }
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<task>() {
            override fun areItemsTheSame(oldItem: task, newItem: task): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: task, newItem: task): Boolean {
                return oldItem == newItem
            }
        }
    }
    interface noteClickDeleteInterface{
        fun onDeleteIconClick(task: task)
        fun itemViewClick(task: task)
    }

 }
