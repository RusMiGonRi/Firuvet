package com.cibertec.demo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CrearCitaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_cita)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // VOLVER

        val ivVolver = findViewById<ImageView>(R.id.ivVolver)

        ivVolver.setOnClickListener {
            irAMenuPrincipal()
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

        // BOTON CREAR CITA

        val btnCrearCita = findViewById<Button>(R.id.btnCrearCita)

        btnCrearCita.setOnClickListener {
            Toast.makeText(this, "Cita Creada con Exito", Toast.LENGTH_SHORT).show()
            irAMenuPrincipal()
        }

    }

    private fun irAMenuPrincipal() {
        val intent = Intent(this, MainActivity::class.java)
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
}