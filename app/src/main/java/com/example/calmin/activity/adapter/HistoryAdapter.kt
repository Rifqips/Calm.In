package com.example.calmin.activity.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.calmin.activity.DashboardActivity
import com.example.calmin.data.model.HistoryDataItem
import com.example.calmin.data.room.HistoryDao
import com.example.calmin.data.room.HistoryDatabase
import com.example.calmin.databinding.ItemHistoryBinding
import com.example.calmin.fragment.HomeFragment
import com.example.calmin.viewmodel.HistoryViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.lang.reflect.Array

class HistoryAdapter(var listHistory : List<HistoryDataItem>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var database : HistoryDatabase? = null
    lateinit var viewModel: HistoryViewModel

    class ViewHolder(var binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
//        private lateinit var listener : On
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        val view = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

        holder.binding.tvDelete.setOnClickListener {
            holder.binding.llOption.isGone = true
            database = HistoryDatabase.getInstance(it.context)
            run{
                GlobalScope.async {
                    database?.historyDao()?.deleteHistory(listHistory[position])
                }
                it.context.startActivity(Intent(it.context, DashboardActivity::class.java))
            }
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