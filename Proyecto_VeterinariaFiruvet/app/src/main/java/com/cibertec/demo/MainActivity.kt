package com.cibertec.demo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        // VOLVER

        val ivVolver = findViewById<ImageView>(R.id.ivVolver)

        ivVolver.setOnClickListener {
            irAInicioSesion()
        }

        // OPCION CONFIGURACION

        val ivConfiguracion = findViewById<ImageView>(R.id.ivConfiguracion)

        ivConfiguracion.setOnClickListener {
            irAConfiguracion()
        }

        // OPCION PERFIL MASCOTA

        val ivPerfilMascota = findViewById<ImageView>(R.id.ivPerfilMascota)

        ivPerfilMascota.setOnClickListener {
            irAPerfilMascota()
        }

        // OPCION PERFIL PERSONAL

        val ivPerfilPersonal = findViewById<ImageView>(R.id.ivPerfilPersonal)

        ivPerfilPersonal.setOnClickListener {
            irAPerfilPersonal()
        }

        // SELECCION CREAR CITA

        val ivCrearCita = findViewById<ImageView>(R.id.ivCrearCita)

        ivCrearCita.setOnClickListener {
            irACrearCita()
        }

        // SELECCION LISTA DE LUGARES

        val ivListaLugares = findViewById<ImageView>(R.id.ivListaLugares)

        ivListaLugares.setOnClickListener {
            irAListaLugares()
        }

        // SELECCION BENEFICIOS

        val ivBeneficios = findViewById<ImageView>(R.id.ivBeneficios)

        ivBeneficios.setOnClickListener {
            irABeneficios()
        }

    }

    private fun irAInicioSesion() {
        val intent = Intent(this, InicioSesionActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun irAConfiguracion() {
        val intent = Intent(this, ConfiguracionActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun irAPerfilMascota() {
        val intent = Intent(this, PerfilMascotaActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun irAPerfilPersonal() {
        val intent = Intent(this, PerfilPersonalActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun irACrearCita() {
        val intent = Intent(this, CrearCitaActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun irAListaLugares() {
        val intent = Intent(this, ListaLugaresActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun irABeneficios() {
        val intent = Intent(this, BeneficiosActivity::class.java)
        startActivity(intent)
        finish()
    }
}