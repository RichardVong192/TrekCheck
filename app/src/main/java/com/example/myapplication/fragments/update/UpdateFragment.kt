package com.example.myapplication.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Update
import com.example.myapplication.R
import com.example.myapplication.model.Item
import com.example.myapplication.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mItemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        mItemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        view.updateItemName_et.setText(args.currentItem.itemName)
        view.updateItemWeight_et.setText(args.currentItem.weight.toString()) //Convert to string because initial value is integer

        view.update_btn.setOnClickListener{
            updateItem()
        }

        //Add menu
        setHasOptionsMenu(true) //add menu to action bar

        return view
    }

    private fun updateItem() {
        val itemName = updateItemName_et.text.toString()
        val itemWeight = updateItemWeight_et.text

        if (inputCheck(itemName, updateItemWeight_et.text)) {
            //Create object
            val updatedItem = Item(args.currentItem.id,
                    itemName,
                    itemWeight.toString().toFloat())
            mItemViewModel.updateItem(updatedItem)
            Toast.makeText(requireContext(), "Item successfully updated!", Toast.LENGTH_LONG).show()
            //Navigate back
            findNavController().navigate(R.id.action_updateFragment_to_ListFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(itemName: String, itemWeight: Editable): Boolean {
        return !(TextUtils.isEmpty(itemName) || itemWeight.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteItem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            mItemViewModel.deleteItem(args.currentItem)
            Toast.makeText(requireContext(), "Successfully deleted: ${args.currentItem.itemName}", Toast.LENGTH_SHORT).show()
            //Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_ListFragment)

        }
        builder.setNegativeButton("No") {_, _ ->}
        builder.setTitle("Delete ${args.currentItem.itemName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentItem.itemName}?")
        builder.create().show()
    }

}