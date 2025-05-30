package com.example.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlin.databinding.ActivitySignupBinding
import java.util.UUID

class SignupActivity : AppCompatActivity() {

    private  lateinit var binding : ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtIngresar2.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegistrar.setOnClickListener{
            Registrar()
        }
    }
    fun Registrar(){
        var id = UUID.randomUUID().toString()
        var iptNombre = binding.iptName.text.toString()
        var iptEmail = binding.iptEmail2.text.toString()
        var iptPassword = binding.iptPassword2.text.toString()
        val persona = Persona(id,iptNombre,iptEmail,iptPassword)

        try {
            val personaExiste = Persistencia().AddUser(this,persona)

            if (!personaExiste){
                Toast.makeText(this, "Se registro persona", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Ya existe este email", Toast.LENGTH_LONG).show()
            }
        }
        catch (e : Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }
}