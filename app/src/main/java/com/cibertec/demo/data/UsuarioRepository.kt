package com.cibertec.demo.data

import com.cibertec.demo.entity.Usuario

object UsuarioRepository {
    val listaUsuarios = mutableListOf<Usuario>()
    var usuarioSesion: Usuario? = null
}