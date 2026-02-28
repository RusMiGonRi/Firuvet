package com.cibertec.demo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

class CitasPendienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_citas_pendiente)

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

        val tvNombre = findViewById<TextView>(R.id.tvNombreMascotaCita)
        val tvLugar = findViewById<TextView>(R.id.tvLugarCita)
        val tvMotivo = findViewById<TextView>(R.id.tvMotivoCita)
        val tvFecha = findViewById<TextView>(R.id.tvFechaCita)
        val tvHora = findViewById<TextView>(R.id.tvHoraCita)
        val tvComentarios = findViewById<TextView>(R.id.tvComentariosCita)

        val mascota = intent.getStringExtra("MASCOTA")
        val lugar = intent.getStringExtra("LUGAR")
        val fecha = intent.getStringExtra("FECHA")
        val hora = intent.getStringExtra("HORA")
        val motivo = intent.getStringExtra("MOTIVO")
        val comentario = intent.getStringExtra("COMENTARIO")

        tvNombre.text = mascota
        tvLugar.text = lugar
        tvMotivo.text = motivo
        tvFecha.text = fecha
        tvHora.text = hora
        tvComentarios.text = comentario ?: "Sin comentarios Adicionales"

        findViewById<Button>(R.id.btnCancelarCita).setOnClickListener {
            val builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("Cancelar Cita")
            builder.setMessage("¿Estas seguro de que deseas Cancelar la Cita de $mascota?")

            builder.setPositiveButton("Cancelarlo") { _, _ ->
                val citaEncontrada = com.cibertec.demo.data.CitaRepository.listaCitas.find {
                    it.mascota == mascota && it.fecha == fecha && it.hora == hora
                }

                if (citaEncontrada != null) {
                    com.cibertec.demo.data.CitaRepository.listaCitas.remove(citaEncontrada)
                    Toast.makeText(this, "Cita Cancelada", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

            builder.setNegativeButton("Mantenerlo") { dialog, _ ->
                dialog.dismiss()
            }

            builder.show()
        }

        findViewById<Button>(R.id.btnVerMapa).setOnClickListener {
            if (!lugar.isNullOrEmpty()) {
                val intent = Intent(this, ListaLugaresActivity::class.java)
                intent.putExtra("LUGAR_A_BUSCAR", lugar)
                startActivity(intent)
            } else {
                Toast.makeText(this, "No hay un lugar Especificado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}