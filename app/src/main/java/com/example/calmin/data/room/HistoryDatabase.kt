package com.example.calmin.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.calmin.data.model.HistoryDataItem

@Database(entities = [HistoryDataItem::class], version = 1 )
abstract class HistoryDatabase : RoomDatabase(){
    abstract fun historyDao(): HistoryDao
    companion object{
        private var INSTANCE : HistoryDatabase? = null
        fun getInstance(context: Context):HistoryDatabase? {
            if (INSTANCE == null){
                synchronized(HistoryDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        HistoryDatabase::class.java,"History.db").build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}