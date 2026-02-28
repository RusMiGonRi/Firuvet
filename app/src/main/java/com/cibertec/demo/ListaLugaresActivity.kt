package com.cibertec.demo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListaLugaresActivity : AppCompatActivity(), com.google.android.gms.maps.OnMapReadyCallback {

    private lateinit var mMap : com.google.android.gms.maps.GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_lugares)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as com.google.android.gms.maps.SupportMapFragment
        mapFragment.getMapAsync(this)

        val rvLugares = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rvLugares)
        val etBuscar = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etBuscarLugar)
        val adapter = com.cibertec.demo.adapter.LugarAdapter(com.cibertec.demo.data.LugarRepository.listaLugares)

        rvLugares.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        rvLugares.adapter = adapter

        etBuscar.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s : CharSequence?, start : Int, count : Int, after : Int) {}
            override fun onTextChanged(s : CharSequence?, start : Int, before : Int, count : Int) {
                val texto = s.toString().lowercase()
                val listaFiltrada = com.cibertec.demo.data.LugarRepository.listaLugares.filter {
                    it.nombre.lowercase().contains(texto) || it.direccion.lowercase().contains(texto)
                }
                adapter.updateLista(listaFiltrada)
            }
            override fun afterTextChanged(s : android.text.Editable?) {}
        })

        findViewById<ImageView>(R.id.ivVolver).setOnClickListener { irAMenuPrincipal() }
        findViewById<ImageView>(R.id.ivConfiguracion).setOnClickListener { irAConfiguracion() }
        findViewById<ImageView>(R.id.ivPerfilMascota).setOnClickListener { irAPerfilMascota() }
        findViewById<ImageView>(R.id.ivPerfilPersonal).setOnClickListener { irAPerfilPersonal() }
    }

    override fun onMapReady(googleMap : com.google.android.gms.maps.GoogleMap) {
        mMap = googleMap
        val lugares = com.cibertec.demo.data.LugarRepository.listaLugares

        for (lugar in lugares) {
            val posicion = com.google.android.gms.maps.model.LatLng(lugar.latitud, lugar.longitud)
            mMap.addMarker(com.google.android.gms.maps.model.MarkerOptions()
                .position(posicion)
                .title(lugar.nombre)
                .snippet(lugar.distancia))
        }

        if (lugares.isNotEmpty()) {
            val primeraUbi = com.google.android.gms.maps.model.LatLng(lugares[0].latitud, lugares[0].longitud)
            mMap.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(primeraUbi, 12f))
        }
    }

    private fun irAMenuPrincipal() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun irAConfiguracion() {
        startActivity(Intent(this, ConfiguracionActivity::class.java))
        finish()
    }

    private fun irAPerfilMascota() {
        startActivity(Intent(this, PerfilMascotaActivity::class.java))
        finish()
    }

    private fun irAPerfilPersonal() {
        startActivity(Intent(this, PerfilPersonalActivity::class.java))
        finish()
    }
}