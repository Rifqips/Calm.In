package com.example.calmin.activity.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.example.calmin.data.model.HistoryDataItem
import com.example.calmin.data.room.HistoryDao
import com.example.calmin.data.room.HistoryDatabase
import com.example.calmin.databinding.ItemHistoryBinding
import java.lang.reflect.Array

class HistoryAdapter(): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {


    private lateinit var context: Context
    var DBHistory: HistoryDatabase? = null
    private lateinit var daoHistory : HistoryDao
    private var listHistory : List<HistoryDataItem> = emptyList()
    private var database : HistoryDatabase? = null

    inner class ViewHolder(var binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
//        private lateinit var listener : On
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        val view = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        database = HistoryDatabase.getInstance(parent.context)
        if (database != null){
            daoHistory = database!!.historyDao()
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        holder.binding.tvTitle.text = listHistory[position].title
        holder.binding.tvDate.text = listHistory[position].dateTime
        holder.binding.tvContent.text = listHistory[position].content

        holder.binding.ivOption.setOnClickListener {
            holder.binding.llOption.isGone = false
        }
        holder.binding.cvItem.setOnClickListener {
            holder.binding.llOption.isGone = true
        }
    }

    override fun getItemCount(): Int {
        return listHistory.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setHistoryData(listHistory: List<HistoryDataItem>){
        this.listHistory = listHistory
        notifyDataSetChanged()
    }
}