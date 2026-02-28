package com.cibertec.demo

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

class ConfiguracionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_configuracion)
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

        findViewById<android.widget.Button>(R.id.btnMetodosPago).setOnClickListener {
            android.widget.Toast.makeText(this, "Funcionalidad de Pagos Próximamente", android.widget.Toast.LENGTH_SHORT).show()
        }

        findViewById<android.widget.Button>(R.id.btnSuscripcion).setOnClickListener {
            android.widget.Toast.makeText(this, "Funcionalidad de Subcripción Próximamente", android.widget.Toast.LENGTH_SHORT).show()
        }

        findViewById<android.widget.Button>(R.id.btnCambiarClave).setOnClickListener {
            android.widget.Toast.makeText(this, "Funcionalidad de Cambiar Contraseña Próximamente", android.widget.Toast.LENGTH_SHORT).show()
        }

        findViewById<android.widget.Button>(R.id.btnAyudaSoporte).setOnClickListener {
            android.widget.Toast.makeText(this, "Funcionalidad de Soporte Próximamente", android.widget.Toast.LENGTH_SHORT).show()
        }

        findViewById<android.widget.Button>(R.id.btnTerminosConfig).setOnClickListener {
            val intent = Intent(this, TerminosCondicionesActivity::class.java)
            startActivity(intent)
        }

        val btnCerrarSesion = findViewById<android.widget.Button>(R.id.btnCerrarSesion)
        btnCerrarSesion.setOnClickListener {
            com.cibertec.demo.data.UsuarioRepository.usuarioSesion = null
            android.widget.Toast.makeText(this, "Sesión Cerrada Correctamente", android.widget.Toast.LENGTH_SHORT).show()

            val intent = Intent(this, InicioSesionActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}