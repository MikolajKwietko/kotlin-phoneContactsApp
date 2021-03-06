package com.example.mytodolist

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val TASK_NAME_PARAM = "task name"
private const val TASK_SURNAME_PARAM = "task surname"
private const val TASK_POS_PARAM = "task pos"

/**
 * A simple [Fragment] subclass.
 * Use the [DeleteDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeleteDialogFragment : DialogFragment() {
    lateinit var mListener: OnDeleteDialogInteractionListener

    // TODO: Rename and change types of parameters
    private var taskNameParam: String? = null
    private var taskSurnameParam: String? = null
    private var taskPosParam: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            taskNameParam = it.getString(TASK_NAME_PARAM)
            taskSurnameParam = it.getString(TASK_SURNAME_PARAM)
            taskPosParam = it.getInt(TASK_POS_PARAM)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DeleteDialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(name: String, surname: String, pos: Int, interactionListener: OnDeleteDialogInteractionListener) =
            DeleteDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(TASK_NAME_PARAM, name)
                    putString(TASK_SURNAME_PARAM, surname)
                    putInt(TASK_POS_PARAM, pos)
                }
                mListener = interactionListener
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setMessage("Delete this entry?" + " $taskNameParam" + " $taskSurnameParam")
        builder.setNegativeButton("Discard", DialogInterface.OnClickListener{ dialogInterface, i ->
            mListener?.onDialogNegativeClick(taskPosParam)
        })
        builder.setPositiveButton("Confirm", DialogInterface.OnClickListener{ dialogInterface, i ->
            mListener?.onDialogPositiveClick(taskPosParam)
        })
        return builder.create()
    }

    interface OnDeleteDialogInteractionListener{
        fun onDialogNegativeClick(pos: Int?)
        fun onDialogPositiveClick(pos: Int?)
    }
}