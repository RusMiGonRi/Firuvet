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

        val especies = arrayOf("Perro", "Gato")
        val adapterEspecie = android.widget.ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, especies)
        val actvEspecie = findViewById<android.widget.AutoCompleteTextView>(R.id.actvEspecie)
        val llContenedorApodos = findViewById<android.widget.LinearLayout>(R.id.llContenedorApodos)
        val llContenedorAlergias = findViewById<android.widget.LinearLayout>(R.id.llContenedorAlergias)

        actvEspecie.setAdapter(adapterEspecie)

        val etNacimientoMascota = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etNacimientoMascota)
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

        val btnIngresar = findViewById<android.widget.Button>(R.id.btnIngresar)
        btnIngresar.setOnClickListener {
            val nombre = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etNombreMascota).text.toString()
            val especie = actvEspecie.text.toString()
            val fechaNacimiento = etNacimientoMascota.text.toString()
            val rgGenero = findViewById<android.widget.RadioGroup>(R.id.rgGeneroMascota)
            val rgEsterilizado = findViewById<android.widget.RadioGroup>(R.id.rgEsterilizado)

            if (nombre.isEmpty() || especie.isEmpty() || fechaNacimiento.isEmpty()) {
                android.widget.Toast.makeText(this, "Por favor, completa los campos obligatorios", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (rgGenero.checkedRadioButtonId == -1) {
                android.widget.Toast.makeText(this, "Por favor, selecciona el género", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (rgEsterilizado.checkedRadioButtonId == -1) {
                android.widget.Toast.makeText(this, "Por favor, indica si está esterilizado", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userSesion = com.cibertec.demo.data.UsuarioRepository.usuarioSesion

            if (userSesion != null) {
                val listaApodos = mutableListOf<String>()
                for (i in 0 until llContenedorApodos.childCount) {
                    val fila = llContenedorApodos.getChildAt(i) as android.widget.LinearLayout
                    val edit = fila.getChildAt(0) as android.widget.EditText
                    if (edit.text.isNotEmpty()) listaApodos.add(edit.text.toString())
                }

                val listaAlergias = mutableListOf<String>()
                for (i in 0 until llContenedorAlergias.childCount) {
                    val fila = llContenedorAlergias.getChildAt(i) as android.widget.LinearLayout
                    val edit = fila.getChildAt(0) as android.widget.EditText
                    if (edit.text.isNotEmpty()) listaAlergias.add(edit.text.toString())
                }

                val genero = if (rgGenero.checkedRadioButtonId == R.id.mrbGeneroHembra) "Hembra" else "Macho"
                val esterilizado = (rgEsterilizado.checkedRadioButtonId == R.id.mrbSiEsterilizado)

                val nuevaMascota = com.cibertec.demo.entity.Mascota(
                    nombre = nombre,
                    especie = especie,
                    genero = genero,
                    fechaNacimiento = fechaNacimiento,
                    esEsterilizado = esterilizado,
                    apodos = listaApodos,
                    alergias = listaAlergias,
                    nickDueño = userSesion.nickUsuario
                )

                com.cibertec.demo.data.MascotaRepository.listaMascotas.add(nuevaMascota)

                android.widget.Toast.makeText(this, "Mascota ${nuevaMascota.nombre} registrada con éxito", android.widget.Toast.LENGTH_SHORT).show()
                irAMenuPrincipal()
            } else {
                android.widget.Toast.makeText(this, "No hay sesión activa", android.widget.Toast.LENGTH_SHORT).show()
            }
        }

        val btnAgregarApodo = findViewById<android.widget.Button>(R.id.btnAgregarApodo)
        btnAgregarApodo.setOnClickListener {
            agregarNuevaFila(llContenedorApodos, "Apodo")
        }

        val btnAgregarAlergia = findViewById<android.widget.Button>(R.id.btnAgregarAlergia)
        btnAgregarAlergia.setOnClickListener {
            agregarNuevaFila(llContenedorAlergias, "Alergia")
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

    private fun agregarNuevaFila(contenedor: android.widget.LinearLayout, pista: String) {
        val nuevaFila = android.widget.LinearLayout(this).apply {
            layoutParams = android.widget.LinearLayout.LayoutParams(
                android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
            )
            orientation = android.widget.LinearLayout.HORIZONTAL
            gravity = android.view.Gravity.CENTER_VERTICAL
            setPadding(0, 0, 0, 8)
        }

        val nuevoEditText = android.widget.EditText(this).apply {
            layoutParams = android.widget.LinearLayout.LayoutParams(0, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            hint = "Nuevo $pista"
            setBackgroundResource(R.drawable.border_border_white_frame)
            setPadding(15, 15, 15, 15)
        }

        val iconoEliminar = android.widget.ImageView(this).apply {
            layoutParams = android.widget.LinearLayout.LayoutParams(90, 90).apply {
                setMargins(15, 0, 0, 0)
            }
            setImageResource(android.R.drawable.ic_delete)
            setColorFilter(android.graphics.Color.WHITE)
            setOnClickListener {
                contenedor.removeView(nuevaFila)
            }
        }

        nuevaFila.addView(nuevoEditText)
        nuevaFila.addView(iconoEliminar)
        contenedor.addView(nuevaFila)
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