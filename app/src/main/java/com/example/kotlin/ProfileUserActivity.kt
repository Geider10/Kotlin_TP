package com.example.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class ProfileUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_user)

        val namePersona = intent.getStringExtra("nameUser")
        val emailPersona = intent.getStringExtra("emailUser")

        Toast.makeText(this, "${namePersona} + ${emailPersona}", Toast.LENGTH_LONG).show()
    }

}