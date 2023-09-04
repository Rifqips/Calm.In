package com.example.calmin.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.calmin.R
import com.example.calmin.activity.DashboardActivity
import com.example.calmin.data.model.HistoryDataItem
import com.example.calmin.data.room.HistoryDatabase
import com.example.calmin.databinding.FragmentAssesmentSevenBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val TAG ="AssesmentSevenFragment"
class AssesmentSevenFragment : Fragment() {

    private lateinit var binding : FragmentAssesmentSevenBinding
    private var dbHistory : HistoryDatabase? = null

    private var q1Score : Int = 0
    private var q2Score : Int = 0
    private var q3Score : Int = 0
    private var q4Score : Int = 0
    private var q5Score : Int = 0
    private var q6Score : Int = 0
    private var story : String = ""
    private var storyTell : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAssesmentSevenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHistory = HistoryDatabase.getInstance(requireActivity())
        assasmentChoosen()
        q1Score = arguments?.getInt("q1")!!
        q2Score = arguments?.getInt("q2")!!
        q3Score = arguments?.getInt("q3")!!
        q4Score = arguments?.getInt("q4")!!
        q5Score = arguments?.getInt("q5")!!
        story = arguments?.getString("story")!!
        storyTell = arguments?.getString("story-tell")!!
    }

    private fun assasmentChoosen(){
        binding.question1.setOnCheckedChangeListener { _, checkedId: Int ->

            if (binding.q1.isChecked) {
                q6Score = 1
            } else if (binding.q2.isChecked) {
                q6Score = 2
            } else if (binding.q3.isChecked) {
                q6Score = 3
            } else if (binding.q4.isChecked) {
                q6Score = 4
            }
        }

        binding.tvNext.setOnClickListener {
            addHistory()
            Toast.makeText(context, "Berhasil menambahkan riwayat", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addHistory(){
        GlobalScope.async {

            val total  = ((q1Score.toFloat() + q2Score.toFloat() + q3Score.toFloat() + q4Score.toFloat() + q5Score.toFloat() + q6Score.toFloat()) / 6)
            val currentDate = getCurrentDate("EEEE, dd MM yyyy")

            val title = story
            val content = storyTell
            val dateTime = currentDate
            val score = total
            dbHistory!!.historyDao().insertHistory(HistoryDataItem(0, title, content,dateTime, score.toString()))
            Log.d(TAG, dbHistory.toString())
            startActivity(Intent(context, DashboardActivity::class.java))
        }
    }
    private fun getCurrentDate(format: String): String {
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        val currentDate = Date()
        return dateFormat.format(currentDate)
    }

}