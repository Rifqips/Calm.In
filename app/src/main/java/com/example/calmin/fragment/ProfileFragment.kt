package com.example.calmin.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
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
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity()
            .getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        binding.tvUsername.text = sharedPreferences.getString("username","")

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireActivity())
                .setTitle("Ingin Keluar Dari Aplikasi ?")
                .setMessage("Username atau password anda akan terhapus")
                .setPositiveButton("Iya"){ _: DialogInterface, i: Int ->
                    startActivity(Intent(context, LoginActivity::class.java))
                    sharedPreferences = requireActivity()
                        .getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    val addUser = sharedPreferences.edit()
                    addUser.clear().apply()
                }
                .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                }
                .show()

        }
    }
}