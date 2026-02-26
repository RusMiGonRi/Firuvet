package com.cibertec.demo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class IngresarMascotaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ingresar_mascota)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ivVolver = findViewById<ImageView>(R.id.ivVolver)
        val ivConfiguracion = findViewById<ImageView>(R.id.ivConfiguracion)
        val ivPerfilMascota = findViewById<ImageView>(R.id.ivPerfilMascota)
        val ivPerfilPersonal = findViewById<ImageView>(R.id.ivPerfilPersonal)

        val especies = arrayOf("Perro", "Gato")
        val adapterEspecie = android.widget.ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, especies)
        val actvEspecie = findViewById<android.widget.AutoCompleteTextView>(R.id.actvEspecie)
        val etNacimientoMascota = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etNacimientoMascota)
        val btnIngresar = findViewById<android.widget.Button>(R.id.btnIngresar)

        actvEspecie.setAdapter(adapterEspecie)

        etNacimientoMascota.setOnClickListener {
            val calendario = java.util.Calendar.getInstance()
            val anio = calendario.get(java.util.Calendar.YEAR)
            val mes = calendario.get(java.util.Calendar.MONTH)
            val dia = calendario.get(java.util.Calendar.DAY_OF_MONTH)

            val dpd = android.app.DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val fechaSeleccionada = "$dayOfMonth/${month + 1}/$year"
                etNacimientoMascota.setText(fechaSeleccionada)
            }, anio, mes, dia)

            dpd.show()
        }

        btnIngresar.setOnClickListener {
            val userSesion = com.cibertec.demo.data.UsuarioRepository.usuarioSesion

            if (userSesion != null) {
                val nombre = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etNombreMascota).text.toString()
                val especie = actvEspecie.text.toString()
                val rgGenero = findViewById<android.widget.RadioGroup>(R.id.rgGeneroMascota)
                val genero = if (rgGenero.checkedRadioButtonId == R.id.mrbGeneroHembra) "Hembra" else "Macho"

                val rgEst = findViewById<android.widget.RadioGroup>(R.id.rgEsterilizado)
                val esterilizado = (rgEst.checkedRadioButtonId == R.id.mrbSiEsterilizado)

                val nuevaMascota = com.cibertec.demo.entity.Mascota(
                    nombre = nombre,
                    especie = especie,
                    genero = genero,
                    fechaNacimiento = etNacimientoMascota.text.toString(),
                    esEsterilizado = esterilizado,
                    nickDueño = userSesion.nickUsuario
                )

                com.cibertec.demo.data.MascotaRepository.listaMascotas.add(nuevaMascota)

                android.widget.Toast.makeText(this, "Mascota ${nuevaMascota.nombre} registrada con éxito", android.widget.Toast.LENGTH_SHORT).show()
                irAMenuPrincipal()
            } else {
                android.widget.Toast.makeText(this, "Error: No hay sesión activa", android.widget.Toast.LENGTH_SHORT).show()
            }
        }

        // VOLVER

        ivVolver.setOnClickListener {
            irAMenuPrincipal()
        }

        // OPCION CONFIGURACION

        ivConfiguracion.setOnClickListener {
            irAConfiguracion()
        }

        // OPCION PERFIL MASCOTA

        ivPerfilMascota.setOnClickListener {
            irAPerfilMascota()
        }

        // OPCION PERFIL PERSONAL

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