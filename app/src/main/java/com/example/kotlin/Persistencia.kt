package com.example.kotlin

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import  android.content.Context
import com.example.kotlin.Entities.Vehiculo
import com.example.kotlin.fragments.SecondFragment

class Persistencia{
    public fun AgregarVehiculo(context: Context, vehiculo: Vehiculo){
        val preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE)
        val editar = preferences.edit()
        val jsonVehiculos = preferences.getString("vehiculos",null)

        val gson = Gson()
        val listaVehiculos : MutableList<Vehiculo>
        if (jsonVehiculos != null) {
            val tipoLista = object : TypeToken<MutableList<Vehiculo>>() {}.type
            listaVehiculos = gson.fromJson(jsonVehiculos, tipoLista)
        }else{
            listaVehiculos = mutableListOf()
        }

        listaVehiculos.add(vehiculo)
        val nuevoJson= gson.toJson(listaVehiculos)
        editar.putString("vehiculos",nuevoJson)
        editar.apply()
    }
    public fun ObtenerVehiculo(context: Context): MutableList<Vehiculo> {
        val preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE)
        val jsonVehiculos = preferences.getString("vehiculos",null)

        val gson = Gson()
        var listaVehiculos : MutableList<Vehiculo>
        val tipoLista = object : TypeToken<MutableList<Vehiculo>>() {}.type
        listaVehiculos = gson.fromJson(jsonVehiculos,tipoLista)

        return listaVehiculos
    }
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
    public fun IngresarPersona(context: Context, email: String, password: String) : Persona?{
        val preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val json = preferences.getString("personas",null)

        val gson = Gson()
        val listaPersonas : ArrayList<Persona>
        val tipoLista = object : TypeToken<ArrayList<Persona>>() {}.type
        listaPersonas = gson.fromJson(json, tipoLista)

        val validarEmailPassword = listaPersonas.firstOrNull{ it.email == email && it.password == password}
        return validarEmailPassword
    }
}