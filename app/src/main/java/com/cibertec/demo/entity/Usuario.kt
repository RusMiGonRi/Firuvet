package com.cibertec.demo.entity

import java.io.Serializable

data class Usuario(
    val nombreCompleto : String,
    var fechaNacimiento : String,
    val nickUsuario : String,
    val claveUsuario : String,
    val aceptoTerminos : Boolean,
    var apellidos : String = "",
    var genero : String = "No Especifico",
    var correo : String = "",
    var dni : String = "",
    var telefono : String = "",
    var direccion : String = ""
) : Serializable