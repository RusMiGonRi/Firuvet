package com.cibertec.demo

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PerfilPersonalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil_personal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNombre = findViewById<android.widget.EditText>(R.id.etNombre)
        val etApellidos = findViewById<android.widget.EditText>(R.id.etApellidos)
        val etFecha = findViewById<android.widget.EditText>(R.id.etFechaNacimiento)
        val etCorreo = findViewById<android.widget.EditText>(R.id.etCorreo)
        val etDni = findViewById<android.widget.EditText>(R.id.etDni)
        val etTelefono = findViewById<android.widget.EditText>(R.id.etTelefono)
        val etDireccion = findViewById<android.widget.EditText>(R.id.etDireccion)
        val mrbIndefinido = findViewById<com.google.android.material.radiobutton.MaterialRadioButton>(R.id.mrbGeneroIndefinido)
        val btnGuardar = findViewById<android.widget.Button>(R.id.btnCrearCita)
        val rgGenero = findViewById<android.widget.RadioGroup>(R.id.rgGenero)
        val user = com.cibertec.demo.data.UsuarioRepository.usuarioSesion

        if (user != null) {
            etNombre.setText(user.nombreCompleto)
            etFecha.setText(user.fechaNacimiento)
            etApellidos.setText(user.apellidos)
            etCorreo.setText(user.correo)
            etDni.setText(user.dni)
            etTelefono.setText(user.telefono)
            etDireccion.setText(user.direccion)

            if (user.genero == "Femenino") {
                findViewById<com.google.android.material.radiobutton.MaterialRadioButton>(R.id.mrbGeneroFemenino).isChecked = true
            } else if (user.genero == "Masculino") {
                findViewById<com.google.android.material.radiobutton.MaterialRadioButton>(R.id.mrbGeneroMasculino).isChecked = true
            } else {
                mrbIndefinido.isChecked = true
            }
        }

        btnGuardar.setOnClickListener {
            if (user != null) {
                user.apellidos = etApellidos.text.toString()
                user.fechaNacimiento = etFecha.text.toString()
                user.correo = etCorreo.text.toString()
                user.dni = etDni.text.toString()
                user.telefono = etTelefono.text.toString()
                user.direccion = etDireccion.text.toString()

                var generoSeleccionado = "No Especifico"
                val idSeleccionado = rgGenero.checkedRadioButtonId

                if (idSeleccionado == R.id.mrbGeneroFemenino) {
                    generoSeleccionado = "Femenino"
                } else if (idSeleccionado == R.id.mrbGeneroMasculino) {
                    generoSeleccionado = "Masculino"
                }

                user.genero = generoSeleccionado

                android.widget.Toast.makeText(this, "Cambios guardados", android.widget.Toast.LENGTH_SHORT).show()
                irAMenuPrincipal()
            }
        }

        etFecha.isFocusable = false
        etFecha.isClickable = true

        etFecha.setOnClickListener {
            val calendario = java.util.Calendar.getInstance()
            val anio = calendario.get(java.util.Calendar.YEAR)
            val mes = calendario.get(java.util.Calendar.MONTH)
            val dia = calendario.get(java.util.Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val fechaSeleccionada = "$dayOfMonth/${month + 1}/$year"
                etFecha.setText(fechaSeleccionada)
            }, anio, mes, dia)

            dpd.show()
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