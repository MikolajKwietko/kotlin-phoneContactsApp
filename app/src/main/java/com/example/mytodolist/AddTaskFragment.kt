package com.example.mytodolist

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mytodolist.data.AVATAR
import com.example.mytodolist.data.AVATAR.*
import com.example.mytodolist.data.TaskItem
import com.example.mytodolist.data.Tasks
import com.example.mytodolist.databinding.FragmentAddTaskBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 * Use the [AddTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTaskFragment : Fragment() {
    val args: AddTaskFragmentArgs by navArgs()
    private lateinit var binding: FragmentAddTaskBinding
    val randomize: Int = (0..15).random()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val resource: Int = when(args.taskToEdit?.avatarid){
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
            null -> when(randomize){
                0 -> R.drawable.avatar_1
                1 -> R.drawable.avatar_2
                2 -> R.drawable.avatar_3
                3 -> R.drawable.avatar_4
                4 -> R.drawable.avatar_5
                5 -> R.drawable.avatar_6
                6 -> R.drawable.avatar_7
                7 -> R.drawable.avatar_8
                8 -> R.drawable.avatar_9
                9 -> R.drawable.avatar_10
                10 -> R.drawable.avatar_11
                11 -> R.drawable.avatar_12
                12 -> R.drawable.avatar_13
                13 -> R.drawable.avatar_14
                14 -> R.drawable.avatar_15
                15 -> R.drawable.avatar_16
                else -> R.drawable.avatar_1
            }
        }
        binding.saveButton.setOnClickListener { saveTask() }
        binding.avatarInput.setImageResource(resource)
        binding.nameInput.setText(args.taskToEdit?.name)
        binding.surnameInput.setText(args.taskToEdit?.surname)
        binding.phoneInput.setText(args.taskToEdit?.phonenumber)
        binding.birthDayInput.setText(args.taskToEdit?.birthdateday)
        binding.birthMonthInput.setText(args.taskToEdit?.birthdatemonth)
        binding.birthYearInput.setText(args.taskToEdit?.birthdateyear)
    }

    private fun saveTask() {
        val rand = when(args.taskToEdit?.avatarid){
            av1 -> 0
            av2 -> 1
            av3 -> 2
            av4 -> 3
            av5 -> 4
            av6 -> 5
            av7 -> 6
            av8 -> 7
            av9 -> 8
            av10 -> 9
            av11 -> 10
            av12 -> 11
            av13 -> 12
            av14 -> 13
            av15 -> 14
            av16 -> 15
            null -> randomize
        }
        var name: String = binding.nameInput.text.toString()
        var surname: String = binding.surnameInput.text.toString()
        var phoneNumber: String = binding.phoneInput.text.toString()
        var birthDay: String = binding.birthDayInput.text.toString()
        var birthMonth: String = binding.birthMonthInput.text.toString()
        var birthYear: String = binding.birthYearInput.text.toString()
        val avatarid = AVATAR.values()[rand]


        if(name.isEmpty()) name = "${Tasks.ITEMS.size + 1} " + getString(R.string.default_name)
        if(surname.isEmpty()) surname = getString(R.string.default_surname)
        if(phoneNumber.isEmpty()) phoneNumber = getString(R.string.default_phone)
        if(birthDay.isEmpty()) birthDay = getString(R.string.default_day)
        if(birthMonth.isEmpty()) birthMonth = getString(R.string.default_month)
        if(birthYear.isEmpty()) birthYear = getString(R.string.default_year)

        val taskItem = TaskItem(
            {name + surname}.hashCode().toString(),
            name,
            surname,
            phoneNumber,
            birthDay,
            birthMonth,
            birthYear,
            avatarid
        )

        if(phoneNumber.length != 9){

            Snackbar.make(binding.root, "Phone number must contain 9 numbers.", Snackbar.LENGTH_SHORT).show()
        } else if ((birthDay.toInt() !in (1..31)) or (birthMonth.toInt() !in (1..12)) or (birthYear.toInt() !in (1900..2022))){
            Snackbar.make(binding.root, "Invalid birth date given.", Snackbar.LENGTH_SHORT).show()
        } else {
            if (!args.edit) {
                Tasks.addTask(taskItem)
            } else {
                Tasks.updateTask(args.taskToEdit, taskItem)
            }

            val inputMethodManager: InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)

            findNavController().popBackStack(R.id.taskFragment, false)
        }





    }
}