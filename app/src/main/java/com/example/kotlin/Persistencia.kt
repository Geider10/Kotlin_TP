package com.example.kotlin

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import  android.content.Context
import com.example.kotlin.Entities.Vehiculo

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
    public fun GetUserByEmail(context: Context, email: String) : Persona{
        val preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val jsonPersonas = preferences.getString("personas",null)

        val gson = Gson()
        val listaPersonas : List<Persona>
        val tipoLista = object : TypeToken<List<Persona>>() {}.type
        listaPersonas = gson.fromJson(jsonPersonas, tipoLista)

        val existsPersona = listaPersonas.firstOrNull{ it.email == email}
        if (existsPersona == null) throw Exception("Usuario no existe")
        return existsPersona
    }

    public fun GetUserById(context: Context, idPersona: String?): Persona {
        val preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE)
        val jsonPersonas = preferences.getString("personas",null)

        val gson = Gson()
        val listaPersonas : List<Persona>
        val tipoLista = object : TypeToken<List<Persona>>() {}.type
        listaPersonas = gson.fromJson(jsonPersonas,tipoLista)

        val existsPersona  = listaPersonas.first { it.id == idPersona}
        if (existsPersona == null) throw Exception("User no existe")
        return  existsPersona
    }
}