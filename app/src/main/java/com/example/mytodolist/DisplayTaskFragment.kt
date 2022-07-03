package com.example.mytodolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mytodolist.data.AVATAR.*
import com.example.mytodolist.data.TaskItem
import com.example.mytodolist.databinding.FragmentDisplayTaskBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DisplayTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DisplayTaskFragment : Fragment() {
    val args: DisplayTaskFragmentArgs by navArgs()
    lateinit var binding: FragmentDisplayTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDisplayTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val task: TaskItem = args.task
        val resource = when(task.avatarid){
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
        binding.displayAvatar.setImageResource(resource)
        binding.displayName.text = task.name
        binding.displaySurname.text = task.surname
        binding.displayPhoneNumber.text = task.phonenumber
        binding.displayBirthDate.text = getString(R.string.display_birth, task.birthdateday, task.birthdatemonth, task.birthdateyear)

        binding.displayEdit.setOnClickListener{
            val taskToEdit =
                DisplayTaskFragmentDirections.actionDisplayTaskFragmentToAddTaskFragment(
                    taskToEdit = task,
                    edit = true)
            findNavController().navigate(taskToEdit)
        }
    }
}