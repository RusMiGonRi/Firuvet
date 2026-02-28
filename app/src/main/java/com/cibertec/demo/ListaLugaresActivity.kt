package com.cibertec.demo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListaLugaresActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_lugares)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rvLugares = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rvLugares)
        val etBuscar = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etBuscarLugar)
        val adapter = com.cibertec.demo.adapter.LugarAdapter(com.cibertec.demo.data.LugarRepository.listaLugares)

        rvLugares.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        rvLugares.adapter = adapter

        etBuscar.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val texto = s.toString().lowercase()
                val listaFiltrada = com.cibertec.demo.data.LugarRepository.listaLugares.filter {
                    it.nombre.lowercase().contains(texto) || it.direccion.lowercase().contains(texto)
                }
                adapter.updateLista(listaFiltrada)
            }
            override fun afterTextChanged(s: android.text.Editable?) {}
        })

        val ivVolver = findViewById<ImageView>(R.id.ivVolver)
        ivVolver.setOnClickListener {
            irAMenuPrincipal()
        }

        val ivConfiguracion = findViewById<ImageView>(R.id.ivConfiguracion)
        ivConfiguracion.setOnClickListener {
            irAConfiguracion()
        }

        val ivPerfilMascota = findViewById<ImageView>(R.id.ivPerfilMascota)
        ivPerfilMascota.setOnClickListener {
            irAPerfilMascota()
        }

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