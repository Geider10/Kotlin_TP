package com.example.kotlin

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import  android.content.Context
class Persistencia{

    public fun RegistrarPersona(context: Context,persona : Persona): Boolean {
        val preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        val json = preferences.getString("personas",null)

        val gson = Gson()
        val listaPersonas : MutableList<Persona>
        if(json != null){
            // Paso 5: Definimos el tipo real que queremos recuperar (una lista de Persona)
            val tipoLista = object : TypeToken<MutableList<Persona>>() {}.type
            listaPersonas = gson.fromJson(json,tipoLista) // Paso 6: Convertimos el JSON a una lista de objetos Persona
        } else{
            listaPersonas = mutableListOf()
        }

        val personaExiste = listaPersonas.any { it.email == persona.email }
        if(!personaExiste) {
            listaPersonas.add(persona)
            val nuevoJson = gson.toJson(listaPersonas)
            editor.putString("personas", nuevoJson)
            editor.apply()
        }
        return personaExiste
    }
}