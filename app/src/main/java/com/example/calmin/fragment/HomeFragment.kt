package com.example.calmin.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calmin.activity.adapter.HistoryAdapter
import com.example.calmin.data.room.HistoryDatabase
import com.example.calmin.databinding.FragmentHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var historyDB : HistoryDatabase? = null
    private val adapterHistory : HistoryAdapter = HistoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyDB = HistoryDatabase.getInstance(requireContext())
        binding.rvHistory.adapter = adapterHistory
        binding.rvHistory.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.rvHistory.isNestedScrollingEnabled = false
        historyAdapter()

        sharedPreferences = requireActivity()
            .getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        binding.tvUsername.text = sharedPreferences.getString("username","")

    }

    private fun historyAdapter(){
        GlobalScope.launch {
            val listHistory = historyDB?.historyDao()?.getDataHistory()
            requireActivity().runOnUiThread {
                listHistory.let {
                    adapterHistory.setHistoryData(it!!)
                }
            }
        }
    }
}