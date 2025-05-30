package com.example.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.kotlin.databinding.ActivityHomeBinding
import com.example.kotlin.fragments.OneFragment
import com.example.kotlin.fragments.SecondFragment
import com.google.android.material.navigation.NavigationView
import org.w3c.dom.Text

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var persona : Persona

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout
        val toolBar = binding.toolBar
        setSupportActionBar(toolBar)


        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.nav_drawer_home_open,R.string.nav_drawer_home_close)
        drawerLayout.addDrawerListener(toggle)

        binding.navigationView.setNavigationItemSelectedListener(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_burger)

        if(savedInstanceState == null){
            replaceFragment(OneFragment())
            binding.navigationView.setCheckedItem(R.id.nav_item_one)
        }

        GetUserById()
        val headerMenuDrawer = binding.navigationView.getHeaderView(0)
        val tvNameUser = headerMenuDrawer.findViewById<TextView>(R.id.tvNameUser)
        tvNameUser.text = persona.nombre
        val tvPerfil = headerMenuDrawer.findViewById<TextView>(R.id.tvPerfil)
        tvPerfil.setOnClickListener{
            val intent = Intent(this, ProfileUserActivity::class.java)
            intent.putExtra("nameUser", persona.nombre)
            intent.putExtra("emailUser", persona.email)
            startActivity(intent)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_item_one -> {
                replaceFragment(OneFragment())
            }
            R.id.nav_item_two -> {
                replaceFragment(SecondFragment())
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    private fun GetUserById() {
        val preferences = getSharedPreferences("user", MODE_PRIVATE)
        val idPersona = preferences.getString("token", null)
        try {
            val user = Persistencia().GetUserById(this, idPersona)
            persona = user
            Log.i("HeaderMenuD", "${persona.nombre} ${persona.email}")
        }
        catch (e : Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }
}