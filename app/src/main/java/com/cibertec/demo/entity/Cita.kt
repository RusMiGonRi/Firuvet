package com.cibertec.demo.entity

data class Cita(
    val idUsuario : Int,
    val mascota : String,
    val lugar : String,
    val fecha : String,
    val hora : String,
    val motivo : String,
    val comentario : String?
)