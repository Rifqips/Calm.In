package com.example.calmin.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.calmin.R
import com.example.calmin.databinding.ActivityDashboardBinding
import com.example.calmin.fragment.HomeFragment
import com.example.calmin.fragment.ProfileFragment

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDashboardBinding

    private val fragmentHome = HomeFragment()
    private val fragmentProfile = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNav.background = null
        loadFragment(fragmentHome)

        binding.fab.setOnClickListener {
            startActivity(Intent(this, AssasmentActivity::class.java))
        }

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuHome ->
                    loadFragment(fragmentHome)
                R.id.menuProfile ->
                    loadFragment(fragmentProfile)
            }
            true
        }
    }

    private  fun loadFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_dashboard, fragment)
            .commit()
    }
    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Tutup Aplikasi")
            .setMessage("Ingin Keluar Dari Aplikasi ?")
            .setPositiveButton("Iya"){ _: DialogInterface, i: Int ->
                finishAffinity()
            }
            .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }
            .show()
    }

}