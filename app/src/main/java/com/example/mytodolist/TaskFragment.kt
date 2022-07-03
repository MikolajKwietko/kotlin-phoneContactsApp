package com.example.mytodolist

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import com.example.mytodolist.data.Tasks
import com.example.mytodolist.databinding.FragmentItemListBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A fragment representing a list of Items.
 */
class TaskFragment : Fragment(), ToDoListListener,
    DeleteDialogFragment.OnDeleteDialogInteractionListener {
    private lateinit var binding: FragmentItemListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemListBinding.inflate(inflater, container, false)

        // Set the adapter
        with(binding.list) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyTaskRecyclerViewAdapter(Tasks.ITEMS, this@TaskFragment)
        }

        binding.addButton.setOnClickListener{ addButtonClick() }

        return binding.root
    }

    private fun addButtonClick() {
        findNavController().navigate(R.id.action_taskFragment_to_addTaskFragment)
    }

    override fun onItemClick(position: Int) {
        val actionTaskFragmentToDisplayTaskFragment =
            TaskFragmentDirections.actionTaskFragmentToDisplayTaskFragment(Tasks.ITEMS.get(position))
                findNavController().navigate(actionTaskFragmentToDisplayTaskFragment)
    }

    override fun onDeleteItemClick(position: Int) {
        showDeleteDialog(position)
    }

    override fun onItemLongClick(position: Int) {
        checkPermissions()
        makeCall(position)
    }

    private fun checkPermissions() {
        if(ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CALL_PHONE), 101)
        }
    }

    private fun makeCall(position: Int) {
        val callNumber = Tasks.ITEMS.get(position).phonenumber
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$callNumber")
        startActivity(intent)
    }

    private fun showDeleteDialog(position: Int) {
        val deleteDialog = DeleteDialogFragment.newInstance(Tasks.ITEMS.get(position).name, Tasks.ITEMS.get(position).surname, position, this)
        deleteDialog.show(requireActivity().supportFragmentManager,"DeleteDialog")
    }

    override fun onDialogPositiveClick(pos: Int?) {
        Tasks.ITEMS.removeAt(pos!!)
        Snackbar.make(requireView(), "Task deleted", Snackbar.LENGTH_LONG).show()
        notifyDataSetChanged()
    }

    private fun notifyDataSetChanged() {
        val rvAdapter = binding.list.adapter
        rvAdapter?.notifyDataSetChanged()
    }

    override fun onDialogNegativeClick(pos: Int?) {
        Snackbar.make(requireView(), "Delete cancelled", Snackbar.LENGTH_LONG).setAction("Redo", View.OnClickListener { showDeleteDialog(pos!!) }).show()
    }

}