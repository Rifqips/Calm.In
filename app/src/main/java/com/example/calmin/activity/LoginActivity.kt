package com.example.calmin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.ImageView
import com.example.calmin.R
import com.example.calmin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.showPassBtn.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            togglePasswordVisibility()
        }

        binding.btnAction.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Show the password
            binding.etPassword.inputType = InputType.TYPE_CLASS_TEXT
            binding.showPassBtn.setImageResource(R.drawable.baseline_remove_red_eye_24) // Replace with your hide password icon
        } else {
            // Hide the password
            binding.etPassword.inputType =
                InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
            binding.showPassBtn.setImageResource(R.drawable.baseline_remove_red_eye_24) // Replace with your show password icon
        }

        // Move the cursor to the end of the password field to maintain cursor position
        binding.etPassword.setSelection(binding.etPassword.text.length)
    }
}