package com.project.onscreen.views.viewHolder
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.onscreen.databinding.OnscreenEmployeeListBinding
import com.project.onscreen.domain.model.EmployeeDomainModel

class OnScreenViewHolder(val onscreenEmployeeListBinding: OnscreenEmployeeListBinding) :
    RecyclerView.ViewHolder(onscreenEmployeeListBinding.root) {
    fun bind(item: EmployeeDomainModel, fn: () -> Unit) {
        with(onscreenEmployeeListBinding){
            textViewUserName.text = item.name
            Glide.with(imageViewAvatar.context)
                .load(item.avatar)
                .into(imageViewAvatar)
            textViewUserEmail.text = item.email
            next.setOnClickListener {
                fn.invoke()
            }
        }
    }
}