package com.project.onscreen.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.onscreen.R
import com.project.onscreen.data.model.Employee
import com.project.onscreen.databinding.OnscreenEmployeeListBinding

class OnScreenAdapter(val employees: ArrayList<Employee>?, val fn: () -> Unit) :
    RecyclerView.Adapter<OnScreenViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnScreenViewHolder {
        val binding =
            OnscreenEmployeeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = OnScreenViewHolder(binding.root)
        holder.bind(binding)
        return holder
    }

    override fun onBindViewHolder(holder: OnScreenViewHolder, position: Int) {
        holder.onscreenEmployeeListBinding.textViewUserName.text = employees?.get(position)?.name
        holder.onscreenEmployeeListBinding.textViewUserEmail.text = employees?.get(position)?.email
        holder.onscreenEmployeeListBinding.next.text = employees?.get(position)?.like
        Glide.with(holder.onscreenEmployeeListBinding.imageViewAvatar.context)
            .load(employees?.get(position)?.avatar)
            .into(holder.onscreenEmployeeListBinding.imageViewAvatar)

        holder.onscreenEmployeeListBinding.next.setOnClickListener {
          if(employees?.get(position)?.like.equals("Like-Animes"))
            fn.invoke()
        }
    }

    override fun getItemCount(): Int {
        return employees?.size ?: 0
    }

    fun addItem(it: List<Employee>) {
        employees?.addAll(it)
    }

    fun clearItem() {
        employees?.clear()
    }
}

class OnScreenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var onscreenEmployeeListBinding: OnscreenEmployeeListBinding
    fun bind(onscreenEmployeeListBinding: OnscreenEmployeeListBinding) {
        this.onscreenEmployeeListBinding = onscreenEmployeeListBinding
    }

}