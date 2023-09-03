package com.example.calmin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.calmin.R
import com.example.calmin.databinding.FragmentAssesmentFourBinding

class AssesmentFourFragment : Fragment() {

    private lateinit var binding : FragmentAssesmentFourBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAssesmentFourBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvNext.setOnClickListener {
            findNavController().navigate(R.id.action_assesmentFourFragment_to_assesmentFiveFragment)
        }
    }
}