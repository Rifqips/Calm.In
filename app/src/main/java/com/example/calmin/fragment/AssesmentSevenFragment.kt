package com.example.calmin.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.calmin.R
import com.example.calmin.activity.DashboardActivity
import com.example.calmin.databinding.FragmentAssesmentSevenBinding

class AssesmentSevenFragment : Fragment() {

    private lateinit var binding : FragmentAssesmentSevenBinding

    var q1Score : Int = 0
    var q2Score : Int = 0
    var q3Score : Int = 0
    var q4Score : Int = 0
    var q5Score : Int = 0
    var q6Score : Int = 0
    var story : String = ""
    var storyTell : String = ""

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
            val total  = ((q1Score.toFloat() + q2Score.toFloat() + q3Score.toFloat() + q4Score.toFloat() + q5Score.toFloat() + q6Score.toFloat()) / 6)
            Toast.makeText(context, "$story $storyTell", Toast.LENGTH_SHORT).show()
        }
    }

}