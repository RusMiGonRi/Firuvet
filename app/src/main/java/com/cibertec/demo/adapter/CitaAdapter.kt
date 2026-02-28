package com.cibertec.demo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.demo.R
import com.cibertec.demo.entity.Cita

class CitaAdapter(
    private val listaCitas: List<Cita>,
    private val onItemClick: (Cita) -> Unit
) : RecyclerView.Adapter<CitaAdapter.CitaViewHolder>() {

    class CitaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMascota: TextView = view.findViewById(R.id.tvMascotaCita)
        val tvFechaHora: TextView = view.findViewById(R.id.tvFechaHoraCita)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cita, parent, false)
        return CitaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CitaViewHolder, position: Int) {
        val cita = listaCitas[position]
        holder.tvMascota.text = cita.mascota
        holder.tvFechaHora.text = "${cita.fecha} - ${cita.hora}"
        holder.itemView.setOnClickListener {
            onItemClick(cita)
        }
    }

    override fun getItemCount() = listaCitas.size
}