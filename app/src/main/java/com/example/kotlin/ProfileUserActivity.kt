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

        val namePerson = intent.getStringExtra("nameUser")
        val emailPerson = intent.getStringExtra("emailUser")
        binding.tvNameUserProfile.text = "Nombre: " + namePerson
        binding.tvEmailUserProfile.text ="Email: " + emailPerson

        binding.btnCerrarSesion.setOnClickListener { LogOut() }
    }

    fun LogOut(){
        val preferences = getSharedPreferences("user", MODE_PRIVATE)
        val edit = preferences.edit()
        edit.putString("token","")
        edit.apply()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}