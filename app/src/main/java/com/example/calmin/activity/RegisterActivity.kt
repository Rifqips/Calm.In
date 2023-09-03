package com.example.calmin.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import com.example.calmin.R
import com.example.calmin.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var sharedPreferences: SharedPreferences
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

        binding.btnRegister.setOnClickListener{
            saveData()
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

    private fun saveData(){
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        if (username != "" && password != ""){
            val addUser = sharedPreferences.edit()
            addUser.putString("username", username)
            addUser.putString("password", password)
            addUser.apply()
            Toast.makeText(this, "Berhasil Registrasi", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        } else{
            Toast.makeText(this, "Gagal Registrasi", Toast.LENGTH_SHORT).show()
        }
    }
}