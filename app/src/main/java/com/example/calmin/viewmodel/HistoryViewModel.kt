package com.example.calmin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.calmin.data.model.HistoryDataItem
import com.example.calmin.data.room.HistoryDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HistoryViewModel(app: Application) : AndroidViewModel(app)  {

    lateinit var allHistory: MutableLiveData<List<HistoryDataItem>>

    init {
        allHistory = MutableLiveData()
        getAllHistory()
    }

    fun getAllHistoryObserve() : MutableLiveData<List<HistoryDataItem>>{
        return allHistory
    }

    fun getAllHistory(){
        GlobalScope.launch {
            val historyDao = HistoryDatabase.getInstance(getApplication())!!.historyDao()
            val listHistory = historyDao.getDataHistory()
            allHistory.postValue(listHistory)
        }
    }

}