package com.project.onscreen.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.onscreen.databinding.OnscreenEmployeeListBinding
import com.project.onscreen.domain.model.EmployeeDomainModel
import com.project.onscreen.views.viewHolder.OnScreenViewHolder

class OnScreenAdapter(val fn: () -> Unit) : RecyclerView.Adapter<OnScreenViewHolder>() {
    var employeeList= mutableListOf<EmployeeDomainModel>()

     fun setDataList(employees:List<EmployeeDomainModel>){
        employeeList=employees as MutableList<EmployeeDomainModel>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnScreenViewHolder = OnScreenViewHolder(OnscreenEmployeeListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: OnScreenViewHolder, position: Int) {
        holder.bind(employeeList[position], fn)
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }


}