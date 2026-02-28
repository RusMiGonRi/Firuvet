package com.cibertec.demo

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cibertec.demo.entity.Usuario
import java.util.Calendar

class RegistrarCuentaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar_cuenta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etRegistroNombre = findViewById<EditText>(R.id.etRegistroNombre)
        val etRegistroFecha = findViewById<EditText>(R.id.etRegistroFecha)
        val etRegistroNick = findViewById<EditText>(R.id.etRegistroNick)
        val etRegistroClave = findViewById<EditText>(R.id.etRegistroClave)
        val etRegistroConfirmarClave = findViewById<EditText>(R.id.etRegistroConfirmarClave)
        val chbTerminos = findViewById<CheckBox>(R.id.chbTerminos)
        val btnCrearCuenta = findViewById<Button>(R.id.btnCrearCuenta)
        val tvTengoCuenta = findViewById<TextView>(R.id.tvTengoCuenta)

        etRegistroFecha.isFocusable = false
        etRegistroFecha.isClickable = true

        etRegistroFecha.setOnClickListener {
            val calendario = Calendar.getInstance()
            val anio = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val fechaSeleccionada = "$dayOfMonth/${month + 1}/$year"
                etRegistroFecha.setText(fechaSeleccionada)
            }, anio, mes, dia)

            dpd.show()
        }

        val textoCompleto = "Acepto los Términos y Condiciones"
        val mSpannableString = SpannableString(textoCompleto)
        val inicio = textoCompleto.indexOf("Términos y Condiciones")
        val fin = inicio + "Términos y Condiciones".length

        if (inicio != -1) {
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val intent = Intent(this@RegistrarCuentaActivity, TerminosCondicionesActivity::class.java)
                    startActivity(intent)
                }
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = true
                    ds.isFakeBoldText = true
                    ds.color = Color.WHITE
                }
            }
            mSpannableString.setSpan(clickableSpan, inicio, fin, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        chbTerminos.text = mSpannableString
        chbTerminos.movementMethod = LinkMovementMethod.getInstance()

        btnCrearCuenta.setOnClickListener {
            val nombre = etRegistroNombre.text.toString().trim()
            val fecha = etRegistroFecha.text.toString().trim()
            val nick = etRegistroNick.text.toString().trim()
            val clave = etRegistroClave.text.toString().trim()
            val confirmarClave = etRegistroConfirmarClave.text.toString().trim()
            val terminos = chbTerminos.isChecked

            if (nombre.isEmpty() || fecha.isEmpty() || nick.isEmpty() || clave.isEmpty()) {
                Toast.makeText(this, "Completa todos los Campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (clave.length < 6) {
                Toast.makeText(this, "La Contraseña debe tener al menos 6 Caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (clave != confirmarClave) {
                Toast.makeText(this, "Las Contraseñas no Coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!terminos) {
                Toast.makeText(this, "Debes aceptar los Términos y Condiciones", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nuevoId = com.cibertec.demo.data.UsuarioRepository.listaUsuarios.size + 1
            val nuevoUsuario = Usuario(
                id = nuevoId,
                nombreCompleto = nombre,
                fechaNacimiento = fecha,
                nickUsuario = nick,
                claveUsuario = clave,
                aceptoTerminos = terminos
            )
            com.cibertec.demo.data.UsuarioRepository.listaUsuarios.add(nuevoUsuario)

            Toast.makeText(this, "Usuario ${nuevoUsuario.nickUsuario} Registrado", Toast.LENGTH_LONG).show()
            irAIniciarSesion()
        }

        tvTengoCuenta.paintFlags = tvTengoCuenta.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        tvTengoCuenta.setOnClickListener {
            irAIniciarSesion()
        }
    }

    private fun irAIniciarSesion() {
        val intent = Intent(this, InicioSesionActivity::class.java)
        startActivity(intent)
        finish()
    }
}