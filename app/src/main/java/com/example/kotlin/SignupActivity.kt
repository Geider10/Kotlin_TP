package com.example.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin.databinding.ActivitySignupBinding

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

        fun saveData(){
            var prefs = getSharedPreferences("user", MODE_PRIVATE)
            with(prefs.edit()){
                putString("name",binding.iptName.text.toString())
                putString("email",binding.iptEmail2.text.toString())
                putString("password",binding.iptPassword2.text.toString())
                apply()
            }
        }
        binding.btnRegistrar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            saveData()
        }
    }
}