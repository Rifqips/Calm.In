package com.example.calmin.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmin.R
import com.example.calmin.activity.DashboardActivity
import com.example.calmin.databinding.FragmentAssesmentSevenBinding

class AssesmentSevenFragment : Fragment() {

    private lateinit var binding : FragmentAssesmentSevenBinding

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

        binding.tvNext.setOnClickListener {
            startActivity(Intent(context, DashboardActivity::class.java))
        }
    }

}