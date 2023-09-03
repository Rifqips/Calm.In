package com.example.calmin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import com.example.calmin.R
import com.example.calmin.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private var isPasswordVisible = false
    private var isPasswordVisibleConfirm = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.showPassBtn.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            togglePasswordVisibility()
        }

        binding.showPassBtnConfirm.setOnClickListener {
            isPasswordVisibleConfirm = !isPasswordVisibleConfirm
            togglePasswordVisibilityConfirm()
        }

        binding.btnLogin.setOnClickListener {
            onBackPressed()
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

    private fun togglePasswordVisibilityConfirm() {
        if (isPasswordVisibleConfirm) {
            // Show the password
            binding.etConfirmPassword.inputType = InputType.TYPE_CLASS_TEXT
            binding.showPassBtnConfirm.setImageResource(R.drawable.baseline_remove_red_eye_24) // Replace with your hide password icon
        } else {
            // Hide the password
            binding.etConfirmPassword.inputType =
                InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
            binding.showPassBtnConfirm.setImageResource(R.drawable.baseline_remove_red_eye_24) // Replace with your show password icon
        }

        // Move the cursor to the end of the password field to maintain cursor position
        binding.etConfirmPassword.setSelection(binding.etConfirmPassword.text.length)
    }
}