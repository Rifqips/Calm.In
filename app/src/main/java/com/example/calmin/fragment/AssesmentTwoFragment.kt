package com.example.calmin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.isGone
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.calmin.R
import com.example.calmin.databinding.FragmentAssesmentTwoBinding

class AssesmentTwoFragment : Fragment() {

    private lateinit var binding : FragmentAssesmentTwoBinding
    var q1score : Int = 0
    var story : String = ""
    var storyTell : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAssesmentTwoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        assasmentChoosen()

        story = arguments?.getString("story")!!
        storyTell = arguments?.getString("story-tell")!!


    }

    private fun assasmentChoosen(){
        binding.question1.setOnCheckedChangeListener { _, checkedId: Int ->

            binding.tvNext.isGone = false
            if (binding.q1.isChecked) {
                q1score = 1
            } else if (binding.q2.isChecked) {
                q1score = 2
            } else if (binding.q3.isChecked) {
                q1score = 3
            } else if (binding.q4.isChecked) {
                q1score = 4
            }
        }

        binding.tvNext.setOnClickListener {
            val arg = Bundle()
            arg.putInt("q1", q1score)
            arg.putString("story", story)
            arg.putString("story-tell", storyTell)
            findNavController().navigate(R.id.action_assesmentTwoFragment_to_assesmentThreeFragment, arg)
        }
    }
}