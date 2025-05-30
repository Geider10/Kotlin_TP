package com.example.kotlin

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import  android.content.Context
import com.example.kotlin.Entities.Persona
import com.example.kotlin.Entities.Vehiculo

class Persistencia{
    public fun AddVehicle(context: Context, vehiculo: Vehiculo){
        val preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE)
        val edit = preferences.edit()
        val jsonVehicles = preferences.getString("vehiculos",null)

        val gson = Gson()
        val listVehicles : MutableList<Vehiculo>
        if (jsonVehicles != null) {
            val tipoLista = object : TypeToken<MutableList<Vehiculo>>() {}.type
            listVehicles = gson.fromJson(jsonVehicles, tipoLista)
        }else{
            listVehicles = mutableListOf()
        }

        val existsVehicle = listVehicles.any {it.Matricula == vehiculo.Matricula}
        if (existsVehicle) throw  Exception ("La matricula ya esta registrada")

        listVehicles.add(vehiculo)
        val updateJson= gson.toJson(listVehicles)
        edit.putString("vehiculos",updateJson)
        edit.apply()
    }
    public fun AddUser(context: Context,persona : Persona){
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
        if (existsPerson) throw Exception("El email ya esta registrado")

        listPersons.add(persona)
        val nuevoJson = gson.toJson(listPersons)
        edit.putString("personas", nuevoJson)
        edit.apply()
    }
    public fun GetUserByEmail(context: Context, email: String) : Persona {
        val preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val jsonPersons = preferences.getString("personas",null)

        val gson = Gson()
        val listPersons : List<Persona>
        val typeList = object : TypeToken<List<Persona>>() {}.type
        listPersons = gson.fromJson(jsonPersons, typeList)

        val existsPersona = listPersons.firstOrNull{ it.email == email}
        if (existsPersona == null) throw Exception("El email es incorrecto")
        return existsPersona
    }

    public fun GetUserById(context: Context, idPersona: String?): Persona {
        val preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE)
        val jsonPersons = preferences.getString("personas",null)

        val gson = Gson()
        val listPersons : List<Persona>
        val typeList = object : TypeToken<List<Persona>>() {}.type
        listPersons = gson.fromJson(jsonPersons,typeList)

        val existsPerson  = listPersons.firstOrNull() { it.id == idPersona}
        if (existsPerson == null) throw Exception("El id es incorrecto")
        return  existsPerson
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
}