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

        findViewById<ImageView>(R.id.ivVolver).setOnClickListener { irAInicioSesion() }
        findViewById<ImageView>(R.id.ivConfiguracion).setOnClickListener { irAConfiguracion() }
        findViewById<ImageView>(R.id.ivPerfilMascota).setOnClickListener { irAPerfilMascota() }
        findViewById<ImageView>(R.id.ivPerfilPersonal).setOnClickListener { irAPerfilPersonal() }

        findViewById<ImageView>(R.id.ivCrearCita).setOnClickListener { irACrearCita() }
        findViewById<ImageView>(R.id.ivListaLugares).setOnClickListener { irAListaLugares() }
        findViewById<ImageView>(R.id.ivBeneficios).setOnClickListener { irABeneficios() }
        findViewById<ImageView>(R.id.ivCitasPendiente).setOnClickListener { irACitasPendiente() }
    }

    private fun irAInicioSesion() {
        val intent = Intent(this, InicioSesionActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun irAConfiguracion() {
        startActivity(Intent(this, ConfiguracionActivity::class.java))
    }

    private fun irAPerfilMascota() {
        startActivity(Intent(this, PerfilMascotaActivity::class.java))
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

    private fun irACitasPendiente() {
        startActivity(Intent(this, CitasPendienteActivity::class.java))
    }
}