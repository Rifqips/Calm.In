package com.example.calmin.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calmin.activity.DashboardActivity
import com.example.calmin.activity.adapter.HistoryAdapter
import com.example.calmin.data.model.HistoryDataItem
import com.example.calmin.data.room.HistoryDatabase
import com.example.calmin.databinding.FragmentHomeBinding
import com.example.calmin.viewmodel.HistoryViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask


private const val TAG = "HomeFragment"
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var historyDB : HistoryDatabase? = null
    lateinit var adapterHistory : HistoryAdapter
    lateinit var viewModel: HistoryViewModel
    private var timer: Timer? = null
    private var timerTask: TimerTask? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterHistory = HistoryAdapter(emptyList())
        sharedPreferences = requireActivity()
            .getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        binding.tvUsername.text = sharedPreferences.getString("username","")
        historyDB = HistoryDatabase.getInstance(requireContext())
        historyAdapter()
        vmHistory()
    }

    private fun vmHistory(){
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        viewModel.getAllHistoryObserve().observe(requireActivity(),{
            adapterHistory.setHistoryData(it as List<HistoryDataItem>)
        })
    }

    private fun historyAdapter(){
        onAttach(requireActivity())
        binding.rvHistory.adapter = adapterHistory
        binding.rvHistory.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.rvHistory.isNestedScrollingEnabled = false
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