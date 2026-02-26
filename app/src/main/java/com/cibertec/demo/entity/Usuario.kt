package com.cibertec.demo.entity

import java.io.Serializable

data class Usuario(
    val nombreCompleto : String,
    val fechaNacimiento : String,
    val nickUsuario : String,
    val claveUsuario : String,
    val aceptoTerminos : Boolean
) : Serializable