package com.example.calmin.activity

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
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
        this.window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                )
        setContentView(binding.root)
        binding.bottomNav.background = null
        loadFragment(fragmentHome)
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
}