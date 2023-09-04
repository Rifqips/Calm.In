package com.example.calmin.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryDataItem (
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var title : String,
    var content : String,
    var dateTime : String,
    var score : String,
)