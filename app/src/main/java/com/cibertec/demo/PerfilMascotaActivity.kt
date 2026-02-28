package com.cibertec.demo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

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

        findViewById<ImageView>(R.id.ivVolver).setOnClickListener { finish() }

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)

        findViewById<ImageView>(R.id.ivMenu).setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.END)
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.itConfiguracion -> startActivity(Intent(this, ConfiguracionActivity::class.java))
                R.id.itListaMascotas -> startActivity(Intent(this, ListaMascotasActivity::class.java))
                R.id.itPerfilPersonal -> startActivity(Intent(this, PerfilPersonalActivity::class.java))
            }
            drawerLayout.closeDrawer(GravityCompat.END)
            true
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
                findViewById<android.widget.EditText>(R.id.etApodos).setText(mascota.apodos.joinToString(", "))
                findViewById<android.widget.EditText>(R.id.etAlergias).setText(mascota.alergias.joinToString(", "))

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

        val btnGuardarMascota = findViewById<android.widget.Button>(R.id.btnGuardarMascota)
        btnGuardarMascota.setOnClickListener {
            if (nombreMascota != null) {
                val mascotaAEditar = com.cibertec.demo.data.MascotaRepository.listaMascotas.find {
                    it.nombre == nombreMascota
                }

                if (mascotaAEditar != null) {
                    mascotaAEditar.nombre = findViewById<android.widget.EditText>(R.id.etNombreMascota).text.toString()
                    mascotaAEditar.especie = findViewById<android.widget.AutoCompleteTextView>(R.id.actvEspecie).text.toString()
                    mascotaAEditar.genero = if (findViewById<android.widget.RadioButton>(R.id.mrbGeneroHembra).isChecked) "Hembra" else "Macho"
                    mascotaAEditar.esEsterilizado = findViewById<android.widget.RadioButton>(R.id.mrbSiEsterilizado).isChecked

                    val nuevosApodos = findViewById<android.widget.EditText>(R.id.etApodos).text.toString().split(",").map { it.trim() }
                    val nuevasAlergias = findViewById<android.widget.EditText>(R.id.etAlergias).text.toString().split(",").map { it.trim() }

                    mascotaAEditar.apodos.clear()
                    mascotaAEditar.apodos.addAll(nuevosApodos)
                    mascotaAEditar.alergias.clear()
                    mascotaAEditar.alergias.addAll(nuevasAlergias)

                    android.widget.Toast.makeText(this, "Datos de ${mascotaAEditar.nombre} Actualizados", android.widget.Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, ListaMascotasActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}