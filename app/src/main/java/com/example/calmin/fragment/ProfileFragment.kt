package com.example.calmin.fragment

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat.finishAffinity
import com.example.calmin.R
import com.example.calmin.activity.LoginActivity
import com.example.calmin.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireActivity())
                .setTitle("Tutup Aplikasi")
                .setMessage("Ingin Keluar Dari Aplikasi ??")
                .setPositiveButton("Iya"){ _: DialogInterface, i: Int ->
                    finishAffinity(requireActivity())
                }
                .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                }
                .show()

        }
    }
}