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
    public fun AddUser(context: Context,persona : Persona): Boolean {
        val preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val edit = preferences.edit()
        val jsonPersons = preferences.getString("personas",null)

        val gson = Gson()
        val listPersons : MutableList<Persona>
        if(jsonPersons != null){
            val typeList = object : TypeToken<MutableList<Persona>>() {}.type
            listPersons = gson.fromJson(jsonPersons,typeList)
        } else{
            listPersons = mutableListOf()
        }

        val existsPerson = listPersons.any { it.email == persona.email }
        if(!existsPerson) {
            listPersons.add(persona)
            val nuevoJson = gson.toJson(listPersons)
            edit.putString("personas", nuevoJson)
            edit.apply()
        }
        return existsPerson
    }
    public fun GetUserByEmail(context: Context, email: String) : Persona{
        val preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val jsonPersons = preferences.getString("personas",null)

        val gson = Gson()
        val listPersons : List<Persona>
        val typeList = object : TypeToken<List<Persona>>() {}.type
        listPersons = gson.fromJson(jsonPersons, typeList)

        val existsPersona = listPersons.firstOrNull{ it.email == email}
        if (existsPersona == null) throw Exception("Usuario no existe")
        return existsPersona
    }

    public fun GetUserById(context: Context, idPersona: String?): Persona {
        val preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE)
        val jsonPersons = preferences.getString("personas",null)

        val gson = Gson()
        val listPersons : List<Persona>
        val typeList = object : TypeToken<List<Persona>>() {}.type
        listPersons = gson.fromJson(jsonPersons,typeList)

        val existsPerson  = listPersons.first { it.id == idPersona}
        if (existsPerson == null) throw Exception("User no existe")
        return  existsPerson
    }
}