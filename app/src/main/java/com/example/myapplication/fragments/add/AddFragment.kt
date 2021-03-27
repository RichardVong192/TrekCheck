package com.example.myapplication.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.Item
import com.example.myapplication.data.ItemViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mItemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        mItemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }
    private fun insertDataToDatabase() {
        val itemName = addItemName_et.text.toString()
        val itemWeight = addItemWeight_et.text

        if (inputCheck(itemName, itemWeight)) {
            // Create user object
            val item = Item(0, itemName, Integer.parseInt(itemWeight.toString()))
            // Add Data to Database
            mItemViewModel.addItem(item)
            Toast.makeText(requireContext(), "Item successfully added!", Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(itemName: String, itemWeight: Editable): Boolean {
        return !(TextUtils.isEmpty(itemName) && itemWeight.isEmpty())
    }
}