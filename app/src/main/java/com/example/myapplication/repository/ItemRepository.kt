package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.ItemDao
import com.example.myapplication.model.Item

class ItemRepository(private val itemDao: ItemDao) {
    val readAllData: LiveData<List<Item>> = itemDao.readAllData()

    suspend fun addItem(item: Item) {
        itemDao.addItem(item)
    }

    suspend fun updateItem(item: Item) {
        itemDao.updateItem(item)
    }
}