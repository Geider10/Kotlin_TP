package com.example.kotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.persistableBundleOf
import com.example.kotlin.Entities.Vehiculo
import com.example.kotlin.Persistencia
import com.example.kotlin.databinding.FragmentSecondBinding
import java.util.UUID

class SecondFragment : Fragment() {
    private  lateinit var binding: FragmentSecondBinding
    val spinnerColors = listOf("Rojo", "Azul","Blanco","Negro", "Gris")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)

        //spinner
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item, spinnerColors)
        binding.spColor.adapter = adapter


        binding.btnPublicar.setOnClickListener{
            var id = UUID.randomUUID().toString()
            var etMatricula = binding.etGuardarMatricula.text.toString()
            var etMarca = binding.etMarca.text.toString()
            var etModelo = binding.etModelo.text.toString()
            var spColor = binding.spColor.selectedItem.toString()

            val vehiculo = Vehiculo(id,etMatricula,etModelo,etMarca,spColor)
            val persistencia = Persistencia()
            //persistencia.AgregarVehiculo(requireContext(), vehiculo)
            //Toast.makeText(requireContext(), "Se reporto vehiculo ✅", Toast.LENGTH_LONG).show()
            var listaVehiculos = persistencia.ObtenerVehiculo(requireContext())
            Toast.makeText(requireContext(), "${listaVehiculos}", Toast.LENGTH_LONG).show()
            ClearForm()
        }
        return binding.root
    }
    fun ClearForm(){
        binding.etGuardarMatricula.text.clear().toString()
        binding.etMarca.text.clear().toString()
        binding.etModelo.text.clear().toString()
//        binding.spColor.selectedItem.apply(0)

    }



}