package com.example.calmin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.calmin.R
import com.example.calmin.databinding.FragmentAssesmentFiveBinding

class AssesmentFiveFragment : Fragment() {

    private lateinit var binding : FragmentAssesmentFiveBinding
    var q1Score : Int = 0
    var q2Score : Int = 0
    var q3Score : Int = 0
    var q4Score : Int = 0
    var story : String = ""
    var storyTell : String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAssesmentFiveBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        assasmentChoosen()
        q1Score = arguments?.getInt("q1")!!
        q2Score = arguments?.getInt("q2")!!
        q3Score = arguments?.getInt("q3")!!
        story = arguments?.getString("story")!!
        storyTell = arguments?.getString("story-tell")!!
    }


    private fun assasmentChoosen(){
        binding.question1.setOnCheckedChangeListener { _, checkedId: Int ->

            // Question 1
            if (binding.q1.isChecked) {
                q4Score = 1
            } else if (binding.q2.isChecked) {
                q4Score = 2
            } else if (binding.q3.isChecked) {
                q4Score = 3
            } else if (binding.q4.isChecked) {
                q4Score = 4
            }
        }

        binding.tvNext.setOnClickListener {
            val arg = Bundle()
            arg.putInt("q1", q1Score)
            arg.putInt("q2", q2Score)
            arg.putInt("q3", q3Score)
            arg.putInt("q4", q4Score)
            arg.putString("story", story)
            arg.putString("story-tell", storyTell)
            findNavController().navigate(R.id.action_assesmentFiveFragment_to_assesmentSixFragment, arg)
        }
    }
}