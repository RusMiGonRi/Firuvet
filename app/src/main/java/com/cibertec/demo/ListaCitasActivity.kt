package com.cibertec.demo

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class ListaCitasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_citas)
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

        configurarListaCitas()

        val rvCitas = findViewById<RecyclerView>(R.id.rvCitas)
        val todasLasCitas = com.cibertec.demo.data.CitaRepository.listaCitas

        rvCitas.layoutManager = LinearLayoutManager(this)
        rvCitas.adapter = com.cibertec.demo.adapter.CitaAdapter(todasLasCitas) { citaSeleccionada ->
            val intent = Intent(this, CitasPendienteActivity::class.java)
            intent.putExtra("MASCOTA", citaSeleccionada.mascota)
            intent.putExtra("LUGAR", citaSeleccionada.lugar)
            intent.putExtra("FECHA", citaSeleccionada.fecha)
            intent.putExtra("HORA", citaSeleccionada.hora)
            intent.putExtra("MOTIVO", citaSeleccionada.motivo)
            intent.putExtra("COMENTARIO", citaSeleccionada.comentario)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        configurarListaCitas()
    }

    private fun configurarListaCitas() {
        val rvCitas = findViewById<RecyclerView>(R.id.rvCitas)
        val idUsuarioLogueado = com.cibertec.demo.data.UsuarioRepository.usuarioSesion?.id ?: 0
        val citasDelUsuario = com.cibertec.demo.data.CitaRepository.listaCitas.filter {
            it.idUsuario == idUsuarioLogueado
        }

        rvCitas.layoutManager = LinearLayoutManager(this)
        rvCitas.adapter = com.cibertec.demo.adapter.CitaAdapter(citasDelUsuario) { citaSeleccionada ->
            val intent = Intent(this, CitasPendienteActivity::class.java)
            intent.putExtra("MASCOTA", citaSeleccionada.mascota)
            intent.putExtra("LUGAR", citaSeleccionada.lugar)
            intent.putExtra("FECHA", citaSeleccionada.fecha)
            intent.putExtra("HORA", citaSeleccionada.hora)
            intent.putExtra("MOTIVO", citaSeleccionada.motivo)
            intent.putExtra("COMENTARIO", citaSeleccionada.comentario)
            startActivity(intent)
        }
    }
}