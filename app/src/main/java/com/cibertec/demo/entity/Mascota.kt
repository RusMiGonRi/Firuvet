package com.cibertec.demo.entity

import java.io.Serializable

data class Mascota(
    var nombre : String,
    var especie : String,
    var genero : String,
    var fechaNacimiento : String,
    var esEsterilizado : Boolean,
    var apodos : MutableList<String> = mutableListOf(),
    var alergias : MutableList<String> = mutableListOf(),
    val nickDueño : String
) : Serializable