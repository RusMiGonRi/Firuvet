package com.cibertec.demo.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.demo.CrearCitaActivity
import com.cibertec.demo.R
import com.cibertec.demo.entity.Lugar

class LugarAdapter(private var listaLugares : List<Lugar>) : RecyclerView.Adapter<LugarAdapter.LugarViewHolder>() {

    fun updateLista(nuevaLista: List<Lugar>) {
        this.listaLugares = nuevaLista
        notifyDataSetChanged()
    }

    class LugarViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tvNombre : TextView = view.findViewById(R.id.tvNombreLugar)
        val tvDistancia : TextView = view.findViewById(R.id.tvDistanciaLugar)
        val ivCitar : ImageView = view.findViewById(R.id.ivCrearCitaItem)
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : LugarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lugar, parent, false)
        return LugarViewHolder(view)
    }

    override fun onBindViewHolder(holder : LugarViewHolder, position : Int) {
        val lugar = listaLugares[position]

        holder.tvNombre.text = lugar.nombre
        holder.tvDistancia.text = lugar.distancia

        holder.ivCitar.setOnClickListener {
            val intent = Intent(holder.itemView.context, CrearCitaActivity::class.java)
            intent.putExtra("VETERINARIA_NOMBRE", lugar.nombre)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() : Int = listaLugares.size
}