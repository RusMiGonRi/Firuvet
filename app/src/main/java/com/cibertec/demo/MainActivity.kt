package com.cibertec.demo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)

        findViewById<ImageView>(R.id.ivMenu).setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.END)
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.itConfiguracion -> irAConfiguracion()
                R.id.itListaMascotas -> irAListaMascotas()
                R.id.itPerfilPersonal -> irAPerfilPersonal()
            }
            drawerLayout.closeDrawer(GravityCompat.END)
            true
        }

        findViewById<ImageView>(R.id.ivVolver).setOnClickListener { irAInicioSesion() }
        findViewById<ImageView>(R.id.ivCrearCita).setOnClickListener { irACrearCita() }
        findViewById<ImageView>(R.id.ivListaLugares).setOnClickListener { irAListaLugares() }
        findViewById<ImageView>(R.id.ivBeneficios).setOnClickListener { irABeneficios() }
        findViewById<ImageView>(R.id.ivListaCitas).setOnClickListener { irAListaCitas() }
    }

    private fun irAInicioSesion() {
        val intent = Intent(this, InicioSesionActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun irAConfiguracion() {
        startActivity(Intent(this, ConfiguracionActivity::class.java))
    }

    private fun irAListaMascotas() {
        startActivity(Intent(this, ListaMascotasActivity::class.java))
    }

    private fun irAPerfilPersonal() {
        startActivity(Intent(this, PerfilPersonalActivity::class.java))
    }

    private fun irACrearCita() {
        startActivity(Intent(this, CrearCitaActivity::class.java))
    }

    private fun irAListaLugares() {
        startActivity(Intent(this, ListaLugaresActivity::class.java))
    }

    private fun irABeneficios() {
        startActivity(Intent(this, BeneficiosActivity::class.java))
    }

    private fun irAListaCitas() {
        startActivity(Intent(this, ListaCitasActivity::class.java))
    }
}