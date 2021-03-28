package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.model.Item

//Suspend functions use kotlin call routines in view model as well

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("DELETE FROM item_table")
    suspend fun deleteAllItems()

    @Query("SELECT * FROM item_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Item>>


}