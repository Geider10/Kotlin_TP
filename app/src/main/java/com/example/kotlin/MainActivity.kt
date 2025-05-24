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

        fun Ingresar (){
            val iptEmail = binding.iptEmail.text.toString()
            val iptPassword = binding.iptPassword.text.toString()
            val persona = Persistencia().IngresarPersona(this, iptEmail, iptPassword)

            if (persona != null) {
                Toast.makeText(this, "Bienvenido ${persona.nombre}", Toast.LENGTH_LONG).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Email o contraseña incorrectos", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnIngresar.setOnClickListener {
            Ingresar()
        }
    }

}