package com.example.kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.Entities.Vehiculo
import com.example.kotlin.databinding.CardVehicleBinding
import com.example.kotlin.databinding.FragmentOneBinding

class VehiculoAdapter (val listVehicles : List<Vehiculo>) : RecyclerView.Adapter<VehiculoAdapter.VehiculoViewHolder>() {

    //inflar el item xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardVehicleBinding.inflate(inflater,parent,false)
        return  VehiculoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehiculoViewHolder, position: Int) {
        val itemVehicle = listVehicles[position]
        holder.setValues(itemVehicle)
    }

    override fun getItemCount(): Int {
        return listVehicles.size
    }

    class VehiculoViewHolder (private val binding : CardVehicleBinding) : RecyclerView.ViewHolder(binding.root){

        fun setValues (vehiculo: Vehiculo){
            with(binding){
                tvMatriculaAuto.text = vehiculo.Matricula
                tvMarcaAuto.text = vehiculo.Marca
                tvModeloAuto.text = vehiculo.Modelo
                tvColorAuto.text = vehiculo.Color
            }

        }
    }
}