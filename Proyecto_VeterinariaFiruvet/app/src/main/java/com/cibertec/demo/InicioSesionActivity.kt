package com.cibertec.demo

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        // BOTON INICIAR SESION

        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)

        btnIniciarSesion.setOnClickListener {
            irAMenuPrincipal()
        }

        // ENLACE NO TENGO CUENTA

        val tvSinCuenta = findViewById<TextView>(R.id.tvSinCuenta)

        tvSinCuenta.paintFlags = tvSinCuenta.paintFlags or Paint.UNDERLINE_TEXT_FLAG

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
        finish()
    }
}