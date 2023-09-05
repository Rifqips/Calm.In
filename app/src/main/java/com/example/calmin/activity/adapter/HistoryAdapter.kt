package com.example.calmin.activity.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.example.calmin.R
import com.example.calmin.activity.DashboardActivity
import com.example.calmin.data.model.HistoryDataItem
import com.example.calmin.data.room.HistoryDatabase
import com.example.calmin.data.utils.Constants
import com.example.calmin.databinding.ItemHistoryBinding
import com.example.calmin.viewmodel.HistoryViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

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

        holder.binding.tvDetail.setOnClickListener {
            holder.binding.llOption.isGone = true
            val dialog = Constants.dialogHistory(it.context, listHistory[position].score,"", Gravity.CENTER)
            val ivBack = dialog.findViewById<ImageView>(R.id.iv_close)
            val description = dialog.findViewById<TextView>(R.id.tv_description)
            if (listHistory[position].score.toDouble() == 1.0){
                description.text = "Pertahankan tetapi jangan terlalu santai ingat masa depan tidak semudah itu kak :)"
            } else if (listHistory[position].score.toDouble() <= 2.0){
                description.text = "Pertahankan terus kondisi ini, ikan sepat ikan curut bisa yuk!"
            } else if (listHistory[position].score.toDouble() <= 3.0){
                description.text = "Kalo capek istirahat sebentar ya kak, habis itu lanjut lagi :)"
            } else if (listHistory[position].score.toDouble() <= 4.0){
                description.text = "Kamu butuh refreshing lupakan sejenak aktifitas mu dan cukupkan galau mu itu :)"
            }
            ivBack.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show() }
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