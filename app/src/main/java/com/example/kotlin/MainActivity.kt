package com.example.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtRegistrate.setOnClickListener {
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }



        fun Ingresar(){
            val prefs = getSharedPreferences("user", MODE_PRIVATE)
            val email = prefs.getString("email",null)
            val password = prefs.getString("password",null)
            val iptEmail = binding.iptEmail.text.toString()
            val iptPassword = binding.iptPassword.text.toString()
            if(email == iptEmail && password == iptPassword){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Datos invalidos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnIngresar.setOnClickListener {
            Ingresar()
        }
    }

}