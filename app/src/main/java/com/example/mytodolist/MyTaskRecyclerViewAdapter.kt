package com.example.mytodolist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.mytodolist.data.AVATAR.*
import com.example.mytodolist.data.TaskItem


import com.example.mytodolist.databinding.FragmentItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyTaskRecyclerViewAdapter(
    private val values: List<TaskItem>,
    private val eventListener: ToDoListListener
) : RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val resource = when(item.avatarid){
            av1 -> R.drawable.avatar_1
            av2 -> R.drawable.avatar_2
            av3 -> R.drawable.avatar_3
            av4 -> R.drawable.avatar_4
            av5 -> R.drawable.avatar_5
            av6 -> R.drawable.avatar_6
            av7 -> R.drawable.avatar_7
            av8 -> R.drawable.avatar_8
            av9 -> R.drawable.avatar_9
            av10 -> R.drawable.avatar_10
            av11 -> R.drawable.avatar_11
            av12 -> R.drawable.avatar_12
            av13 -> R.drawable.avatar_13
            av14 -> R.drawable.avatar_14
            av15 -> R.drawable.avatar_15
            av16 -> R.drawable.avatar_16
        }
        holder.imgView.setImageResource(resource)
        holder.contentView.text = item.name + " " + item.surname
        holder.itemContainer.setOnClickListener{
            eventListener.onItemClick(position)
        }
        holder.itemContainer.setOnLongClickListener {
            eventListener.onItemLongClick(position)
            return@setOnLongClickListener true
        }
        holder.deleteView.setOnClickListener{
            eventListener.onDeleteItemClick(position)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgView: ImageView = binding.itemImg
        val contentView: TextView = binding.content
        val deleteView: ImageButton = binding.deleteContactButton
        val itemContainer: View = binding.root

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}