package com.project.onscreen.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.onscreen.data.model.Anime
import com.project.onscreen.databinding.AnimeListBinding

class AnimeAdapter(val employees: ArrayList<Anime>?) :
    RecyclerView.Adapter<AnimeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding = AnimeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = AnimeViewHolder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(employees?.get(position))
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

class AnimeViewHolder(val binding: AnimeListBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Anime?) {
        binding.name.text = item?.anime
        binding.character.text = item?.character
        binding.quote.text = item?.quote
    }

}