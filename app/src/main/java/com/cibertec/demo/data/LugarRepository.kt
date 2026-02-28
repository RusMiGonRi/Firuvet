package com.cibertec.demo.data

import com.cibertec.demo.entity.Lugar

object LugarRepository {
    val listaLugares = mutableListOf(
        Lugar(1, "Clínica Veterinaria Central", "2km", "Av. Principal 123", -12.046374, -77.042793),
        Lugar(2, "Pet Shop & Salud", "5km", "Calle Los Pinos 456", -12.050000, -77.050000),
        Lugar(3, "Hospital Veterinario SOS", "1.5km", "Av. Grau 789", -12.060234, -77.035421),
        Lugar(4, "Grooming & Style Mascotas", "3.2km", "Jr. Huallaga 210", -12.045000, -77.030000),
        Lugar(5, "Veterinaria 'El Cachorro'", "800m", "Av. Arequipa 1540", -12.072111, -77.034555),
        Lugar(6, "Centro de Rehabilitación Animal", "4.1km", "Calle Las Flores 332", -12.085000, -77.048000),
        Lugar(7, "Farmacia Veterinaria San José", "6.5km", "Av. Javier Prado 2500", -12.092000, -77.021000),
        Lugar(8, "Clínica de Especialidades Firu-Vet", "10km", "Av. La Marina 4455", -12.075000, -77.100000)
    )
}