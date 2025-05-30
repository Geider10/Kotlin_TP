package com.example.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display.Mode
import android.widget.TextView
import android.widget.Toast
import com.example.kotlin.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AutoLogin()

        binding.txtRegistrate.setOnClickListener {
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }

        binding.btnIngresar.setOnClickListener {
            Ingresar()
        }
    }
    fun Ingresar (){
        val iptEmail = binding.iptEmail.text.toString()
        val iptPassword = binding.iptPassword.text.toString()
        val persona = Persistencia().IngresarPersona(this, iptEmail, iptPassword)

        if (persona != null) {
            NavegarHome()
            CreateToken(persona.id)
        }else{
            Toast.makeText(this, "Email o contraseña incorrectos", Toast.LENGTH_LONG).show()
        }
    }
    fun CreateToken(idPersona : String){
        val preferences = getSharedPreferences("user", MODE_PRIVATE)
        val editor = preferences.edit()

        editor.putString("token",idPersona)
        editor.apply()
    }
    fun AutoLogin(){
        val preferences = getSharedPreferences("user", MODE_PRIVATE)
        val idPersona = preferences.getString("token", "")
        if(!idPersona.isNullOrEmpty()){
            NavegarHome()
        }
    }
    fun NavegarHome(){
        //Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

}