package com.project.onscreen.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.onscreen.data.model.Anime
import com.project.onscreen.databinding.AnimeListBinding
class AnimeAdapter( val employees: ArrayList<Anime>?) :
    RecyclerView.Adapter<AnimeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding = AnimeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = AnimeViewHolder(binding.root)
        holder.bind(binding)
        return holder
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.binding.name.text = employees?.get(position)?.anime
        holder.binding.character.text = employees?.get(position)?.character
        holder.binding.quote.text = employees?.get(position)?.quote
    }

    override fun getItemCount(): Int {
        return employees?.size ?: 0
    }

    fun addItem(it: List<Anime>?) {
        if (it != null) {
            employees?.addAll(it)
        }
    }
    fun clearItems() {
        employees?.clear()
    }
}

class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var binding: AnimeListBinding
    fun bind(binding: AnimeListBinding) {
        this.binding = binding
    }

}