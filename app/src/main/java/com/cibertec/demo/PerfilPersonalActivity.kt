package com.cibertec.demo

import android.app.DatePickerDialog
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

        val etNombre = findViewById<android.widget.EditText>(R.id.etNombre)
        val etApellidos = findViewById<android.widget.EditText>(R.id.etApellidos)
        val etFecha = findViewById<android.widget.EditText>(R.id.etFechaNacimiento)
        val etCorreo = findViewById<android.widget.EditText>(R.id.etCorreo)
        val etDni = findViewById<android.widget.EditText>(R.id.etDni)
        val etTelefono = findViewById<android.widget.EditText>(R.id.etTelefono)
        val etDireccion = findViewById<android.widget.EditText>(R.id.etDireccion)

        val mrbIndefinido = findViewById<com.google.android.material.radiobutton.MaterialRadioButton>(R.id.mrbGeneroIndefinido)
        val rgGenero = findViewById<android.widget.RadioGroup>(R.id.rgGenero)

        val btnGuardarUsuario = findViewById<android.widget.Button>(R.id.btnGuardarUsuario)

        val user = com.cibertec.demo.data.UsuarioRepository.usuarioSesion
        val etCantidadMascotas = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etCantidadMascotas)
        val etCantidadCitas = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etCantidadCitas)

        if (user != null) {
            etNombre.setText(user.nombreCompleto)
            etFecha.setText(user.fechaNacimiento)
            etApellidos.setText(user.apellidos)
            etCorreo.setText(user.correo)
            etDni.setText(user.dni)
            etTelefono.setText(user.telefono)
            etDireccion.setText(user.direccion)

            val misMascotas = com.cibertec.demo.data.MascotaRepository.listaMascotas.filter {
                it.nickDueño == user.nickUsuario
            }
            etCantidadMascotas.setText(misMascotas.size.toString())
            etCantidadMascotas.isEnabled = false

            val misCitas = com.cibertec.demo.data.CitaRepository.listaCitas.filter {
                it.idUsuario == user.id
            }

            etCantidadCitas.setText(misCitas.size.toString())
            etCantidadCitas.isEnabled = false

            if (user.genero == "Femenino") {
                findViewById<com.google.android.material.radiobutton.MaterialRadioButton>(R.id.mrbGeneroFemenino).isChecked = true
            } else if (user.genero == "Masculino") {
                findViewById<com.google.android.material.radiobutton.MaterialRadioButton>(R.id.mrbGeneroMasculino).isChecked = true
            } else {
                mrbIndefinido.isChecked = true
            }
        }

        btnGuardarUsuario.setOnClickListener {
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
    }

    private fun irAMenuPrincipal() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}