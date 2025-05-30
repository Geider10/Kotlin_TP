package com.example.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlin.databinding.ActivityMainBinding

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
        try {
            val person = Persistencia().GetUserByEmail(this, iptEmail)
            val passwordMatched = person.password == iptPassword
            if (!passwordMatched) return Toast.makeText(this, "Contraseña no coincide", Toast.LENGTH_LONG).show()
            NavegarHome()
            CreateToken(person.id)
        }
        catch (e : Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }
    fun CreateToken(idPersona : String){
        val preferences = getSharedPreferences("user", MODE_PRIVATE)
        val edit = preferences.edit()

        edit.putString("token",idPersona)
        edit.apply()
    }
    fun AutoLogin(){
        val preferences = getSharedPreferences("user", MODE_PRIVATE)
        val token = preferences.getString("token", "")
        if(!token.isNullOrEmpty()){
            NavegarHome()
        }
    }
    fun NavegarHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

}