package com.cibertec.demo.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.demo.PerfilMascotaActivity
import com.cibertec.demo.R
import com.cibertec.demo.entity.Mascota

class MascotaAdapter(private val listaMascotas : List<Mascota>) : RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder>() {

    class MascotaViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombreItem)
        val tvEspecie: TextView = view.findViewById(R.id.tvEspecieItem)
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MascotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mascota, parent, false)
        return MascotaViewHolder(view)
    }

    override fun onBindViewHolder(holder : MascotaViewHolder, position : Int) {
        val mascota = listaMascotas[position]

        holder.tvNombre.text = mascota.nombre
        holder.tvEspecie.text = mascota.especie

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PerfilMascotaActivity::class.java)
            intent.putExtra("NOMBRE_MASCOTA", mascota.nombre)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listaMascotas.size
}