package com.example.calmin.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.calmin.R
import com.example.calmin.databinding.FragmentAssesmentOneBinding

class AssesmentOneFragment : Fragment() {

    private lateinit var binding : FragmentAssesmentOneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAssesmentOneBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etStory.doOnTextChanged{text, start, before, count ->
            binding.tvNext.isGone = binding.etStory.text.toString().isEmpty() && binding.etStoryTell.text.toString().isEmpty()
        }

        binding.tvNext.setOnClickListener {
            postDescription()
        }

    }

    private fun postDescription(){
        val arg = Bundle()
        arg.putString("story", binding.etStory.text.toString())
        arg.putString("story-tell", binding.etStoryTell.text.toString())

        findNavController().navigate(R.id.action_assesmentOneFragment_to_assesmentTwoFragment, arg)
    }


}