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

        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item, spinnerColors)
        binding.spColor.adapter = adapter

        binding.btnPublicar.setOnClickListener{
            Publicar()
        }
        return binding.root
    }
    fun Publicar (){
        var id = UUID.randomUUID().toString()
        var etMatricula = binding.etGuardarMatricula.text.toString()
        var etMarca = binding.etMarca.text.toString()
        var etModelo = binding.etModelo.text.toString()
        var spColor = binding.spColor.selectedItem.toString()
        val vehicle = Vehiculo(id,etMatricula,etModelo,etMarca,spColor)

        try {
            val persistence = Persistencia()
            persistence.AddVehicle(requireContext(), vehicle)
            Toast.makeText(requireContext(), "Se reporto el vehiculo", Toast.LENGTH_LONG).show()
            ClearForm()
        }
        catch (e : Exception){
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
        }
    }
    fun ClearForm(){
        binding.etGuardarMatricula.text.clear()
        binding.etMarca.text.clear()
        binding.etModelo.text.clear()
        binding.spColor.setSelection(0)

    }



}