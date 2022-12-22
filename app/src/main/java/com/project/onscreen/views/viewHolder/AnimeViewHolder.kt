package com.project.onscreen.views.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.project.onscreen.databinding.AnimeListBinding
import com.project.onscreen.domain.model.AnimeDomainModel

class AnimeViewHolder(val binding: AnimeListBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: AnimeDomainModel) {
        with(binding){
            name.text = item.anime
            quote.text = item.quote
        }
    }

}