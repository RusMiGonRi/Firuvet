package com.cibertec.demo

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
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        // TERMINOS Y CONDICIONES
        val chbTerminos = findViewById<CheckBox>(R.id.chbTerminos)
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

        // BOTON CREAR CUENTA

        val btnCrearCuenta = findViewById<Button>(R.id.btnCrearCuenta)

        btnCrearCuenta.setOnClickListener {
            irAIniciarSesion()
        }

        // ENLACE YA TENGO CUENTA

        val tvTengoCuenta = findViewById<TextView>(R.id.tvTengoCuenta)

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