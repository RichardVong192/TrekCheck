package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.model.Item

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Query("SELECT * FROM item_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Item>>


}