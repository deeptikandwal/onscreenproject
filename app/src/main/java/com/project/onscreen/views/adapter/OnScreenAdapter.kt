package com.project.onscreen.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.onscreen.data.response.EmployeeListDto
import com.project.onscreen.databinding.OnscreenEmployeeListBinding
import com.project.onscreen.domain.model.Employee

class OnScreenAdapter(val employees: ArrayList<Employee>?, val fn: () -> Unit) :
    RecyclerView.Adapter<OnScreenViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnScreenViewHolder {
        val binding =
            OnscreenEmployeeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = OnScreenViewHolder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: OnScreenViewHolder, position: Int) {
        holder.bind(employees?.get(position), fn)
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

class OnScreenViewHolder(val onscreenEmployeeListBinding: OnscreenEmployeeListBinding) :
    RecyclerView.ViewHolder(onscreenEmployeeListBinding.root) {
    fun bind(item: Employee?, fn: () -> Unit) {
        onscreenEmployeeListBinding.textViewUserName.text = item?.name
        onscreenEmployeeListBinding.textViewUserEmail.text = item?.email
        Glide.with(onscreenEmployeeListBinding.imageViewAvatar.context)
            .load(item?.avatar)
            .into(onscreenEmployeeListBinding.imageViewAvatar)

        onscreenEmployeeListBinding.next.setOnClickListener {
            fn.invoke()
        }
    }

}