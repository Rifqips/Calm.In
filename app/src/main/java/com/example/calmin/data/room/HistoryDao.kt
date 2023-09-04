package com.example.calmin.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.calmin.data.model.HistoryDataItem

@Dao
interface HistoryDao {
    @Insert
    fun insertHistory(history : HistoryDataItem)

    @Query(" SELECT * FROM HistoryDataItem ORDER BY id DESC")
    fun getDataHistory() :List<HistoryDataItem>

    @Delete
    fun deleteHistory(history: HistoryDataItem) : Int

    @Update
    fun updateHistory(history: HistoryDataItem) : Int

}