package com.example.kotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.Entities.Vehiculo
import com.example.kotlin.R
import com.example.kotlin.VehiculoAdapter
import com.example.kotlin.databinding.FragmentOneBinding


class OneFragment : Fragment() {

    private  lateinit var binding: FragmentOneBinding
    private val listVehicles : List<Vehiculo> = listOf(
        Vehiculo("01", "AAAA", "Focus", "Ford", "Blanco"),
        Vehiculo("02", "BBBB", "Duster", "Renault", "Rojo"),
        Vehiculo("03", "CCCC", "C8", "Audi", "Negro")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)

        startRecyclerView()

        return binding.root
    }
    fun startRecyclerView(){
        binding.recyclerVehiculos.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerVehiculos.adapter =  VehiculoAdapter(listVehicles)
    }

}