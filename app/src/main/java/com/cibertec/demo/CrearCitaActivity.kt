package com.cibertec.demo

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar
import java.util.Locale

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

        val actvMascota = findViewById<AutoCompleteTextView>(R.id.actvMascota)
        val actvLugar = findViewById<AutoCompleteTextView>(R.id.actvLugar)
        val etMotivoCita = findViewById<TextInputEditText>(R.id.etMotivoCita)
        val etFechaCita = findViewById<TextInputEditText>(R.id.etFechaCita)
        val etHoraCita = findViewById<TextInputEditText>(R.id.etHoraCita)
        val etComentarioExtra = findViewById<TextInputEditText>(R.id.etComentarioExtra)
        val btnCrearCita = findViewById<Button>(R.id.btnCrearCita)
        val listaNombresMascotas = com.cibertec.demo.data.MascotaRepository.listaMascotas.map { it.nombre }
        val nombresMascotasFinal = listaNombresMascotas.ifEmpty { listOf("No tienes Mascotas") }

        actvMascota.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, nombresMascotasFinal))

        val listaNombresLugares = com.cibertec.demo.data.LugarRepository.listaLugares.map { it.nombre }
        val adapterLugares = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, listaNombresLugares)
        actvLugar.setAdapter(adapterLugares)

        val nombreRecibido = intent.getStringExtra("VETERINARIA_NOMBRE")
        if (!nombreRecibido.isNullOrEmpty()) {
            actvLugar.setText(nombreRecibido, false)
        }

        etFechaCita.setOnClickListener {
            val c = Calendar.getInstance()
            DatePickerDialog(this, { _, year, month, day ->
                val fecha = String.format(Locale.getDefault(), "%02d/%02d/%d", day, month + 1, year)
                etFechaCita.setText(fecha)
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
        }

        etHoraCita.setOnClickListener {
            val c = Calendar.getInstance()
            TimePickerDialog(this, { _, hour, minute ->
                val hora = String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
                etHoraCita.setText(hora)
            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show()
        }

        btnCrearCita.setOnClickListener {
            val mascota = actvMascota.text.toString()
            val lugar = actvLugar.text.toString()
            val motivo = etMotivoCita.text.toString().trim()
            val fecha = etFechaCita.text.toString()
            val hora = etHoraCita.text.toString()
            val comentario = etComentarioExtra.text.toString().trim()

            if (mascota.isEmpty() || mascota == "No tienes Mascotas" || motivo.isEmpty() || fecha.isEmpty() || hora.isEmpty()) {
                Toast.makeText(this, "Completa los campos Obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val idUsuarioActual = com.cibertec.demo.data.UsuarioRepository.usuarioSesion?.id ?: 0

            val nuevaCita = com.cibertec.demo.entity.Cita(
                idUsuario = idUsuarioActual,   // 1. El Int que faltaba
                mascota = mascota,             // 2. String
                lugar = lugar,                 // 3. String
                motivo = motivo,               // 4. String
                fecha = fecha,                 // 5. String
                hora = hora,                   // 6. String
                comentario = comentario.takeIf { it.isNotEmpty() } // 7. String?
            )

            com.cibertec.demo.data.CitaRepository.listaCitas.add(nuevaCita)
            Toast.makeText(this, "Cita para $mascota en $lugar creada con Éxito", Toast.LENGTH_LONG).show()
            irAMenuPrincipal()
        }

        findViewById<ImageView>(R.id.ivVolver).setOnClickListener { finish() }
        findViewById<ImageView>(R.id.ivConfiguracion).setOnClickListener { irAConfiguracion() }
        findViewById<ImageView>(R.id.ivPerfilMascota).setOnClickListener { irAPerfilMascota() }
        findViewById<ImageView>(R.id.ivPerfilPersonal).setOnClickListener { irAPerfilPersonal() }
    }

    private fun irAMenuPrincipal() {
        startActivity(Intent(this, MainActivity::class.java))
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
}