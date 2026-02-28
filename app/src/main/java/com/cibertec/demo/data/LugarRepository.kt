package com.cibertec.demo.data

import com.cibertec.demo.entity.Lugar

object LugarRepository {
    val listaLugares = mutableListOf(
        Lugar(1, "Clinica Veterinaria Central", "2km", "Av. Principal 123", -12.046374, -77.042793),
        Lugar(2, "Pet Shop & Salud", "5km", "Calle Los Pinos 456", -12.050000, -77.050000),
    )
}