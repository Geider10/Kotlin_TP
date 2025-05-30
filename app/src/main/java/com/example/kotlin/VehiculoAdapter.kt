package com.example.kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.Entities.Vehiculo

class VehiculoAdapter (val listVehicles : List<Vehiculo>) : RecyclerView.Adapter<VehiculoAdapter.VehiculoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculoViewHolder {
        val layoutInflate = LayoutInflater.from(parent.context).inflate(R.layout.card_vehicle,parent,false)
        return  VehiculoViewHolder(layoutInflate)
    }

    override fun onBindViewHolder(holder: VehiculoViewHolder, position: Int) {
        val itemVehicle = listVehicles[position]
        holder.setValues(itemVehicle)
    }

    override fun getItemCount(): Int {
        return listVehicles.size
    }

    class VehiculoViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        var registrationVehicle : TextView = itemView.findViewById(R.id.tvMatriculaAuto)
        var brandVehicle : TextView = itemView.findViewById(R.id.tvMarcaAuto)
        var modelVehicle : TextView = itemView.findViewById(R.id.tvModeloAuto)
        var colorVehicle : TextView = itemView.findViewById(R.id.tvColorAuto)

        fun setValues (vehiculo: Vehiculo){
            registrationVehicle.text = vehiculo.Matricula
            brandVehicle.text = vehiculo.Marca
            modelVehicle.text = vehiculo.Modelo
            colorVehicle.text = vehiculo.Color
        }
    }
}