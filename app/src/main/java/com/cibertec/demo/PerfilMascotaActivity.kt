package com.cibertec.demo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PerfilMascotaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil_mascota)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombreMascota = intent.getStringExtra("NOMBRE_MASCOTA")
        if (nombreMascota != null) {
            val mascota = com.cibertec.demo.data.MascotaRepository.listaMascotas.find {
                it.nombre == nombreMascota
            }

            if (mascota != null) {
                findViewById<android.widget.EditText>(R.id.etNombreMascota).setText(mascota.nombre)
                findViewById<android.widget.AutoCompleteTextView>(R.id.actvEspecie).setText(mascota.especie)
                findViewById<android.widget.EditText>(R.id.etNacimientoMascota).setText(mascota.fechaNacimiento)

                if (mascota.genero == "Hembra") {
                    findViewById<android.widget.RadioButton>(R.id.mrbGeneroHembra).isChecked = true
                } else {
                    findViewById<android.widget.RadioButton>(R.id.mrbGeneroMacho).isChecked = true
                }

                if (mascota.esEsterilizado) {
                    findViewById<android.widget.RadioButton>(R.id.mrbSiEsterilizado).isChecked = true
                } else {
                    findViewById<android.widget.RadioButton>(R.id.mrbNoEsterilizado).isChecked = true
                }
            }
        }

        val ivVolver = findViewById<ImageView>(R.id.ivVolver)
        ivVolver.setOnClickListener {
            irAMenuPrincipal()
        }

        val ivConfiguracion = findViewById<ImageView>(R.id.ivConfiguracion)

        ivConfiguracion.setOnClickListener {
            irAConfiguracion()
        }

        val ivPerfilMascota = findViewById<ImageView>(R.id.ivPerfilMascota)
        ivPerfilMascota.setOnClickListener {
            irAPerfilMascota()
        }


        val ivPerfilPersonal = findViewById<ImageView>(R.id.ivPerfilPersonal)
        ivPerfilPersonal.setOnClickListener {
            irAPerfilPersonal()
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