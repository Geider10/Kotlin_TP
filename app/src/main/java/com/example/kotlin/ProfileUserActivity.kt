package com.example.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlin.databinding.ActivityProfileUserBinding

class ProfileUserActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfileUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val namePersona = intent.getStringExtra("nameUser")
        val emailPersona = intent.getStringExtra("emailUser")
        binding.tvNameUserProfile.text = namePersona
        binding.tvEmailUserProfile.text = emailPersona

        binding.btnCerrarSesion.setOnClickListener { LogOut() }
    }

    fun LogOut(){
        val preferences = getSharedPreferences("user", MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString("token","")
        editor.apply()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}