package com.cibertec.demo

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class InicioSesionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio_sesion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNick = findViewById<EditText>(R.id.etNick)
        val etClave = findViewById<EditText>(R.id.etClave)
        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        val tvSinCuenta = findViewById<TextView>(R.id.tvSinCuenta)

        tvSinCuenta.paintFlags = tvSinCuenta.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        btnIniciarSesion.setOnClickListener {
            val usuario = etNick.text.toString().trim()
            val clave = etClave.text.toString().trim()

            if (usuario.isEmpty() || clave.isEmpty()) {
                Toast.makeText(this, "Completa todos los Campos", Toast.LENGTH_SHORT).show()
            } else {
                val encontrado = com.cibertec.demo.data.UsuarioRepository.listaUsuarios.find {
                    it.nickUsuario == usuario && it.claveUsuario == clave
                }

                if (encontrado != null) {
                    com.cibertec.demo.data.UsuarioRepository.usuarioSesion = encontrado

                    Toast.makeText(this, "Bienvenido ${encontrado.nombreCompleto}", Toast.LENGTH_SHORT).show()
                    irAMenuPrincipal()
                } else {
                    Toast.makeText(this, "Usuario o Clave Incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }

        tvSinCuenta.setOnClickListener {
            irARegistrarCuenta()
        }
    }

    private fun irAMenuPrincipal() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun irARegistrarCuenta() {
        val intent = Intent(this, RegistrarCuentaActivity::class.java)
        startActivity(intent)
    }
}