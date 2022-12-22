package com.project.onscreen.views.viewState
import com.project.onscreen.domain.model.EmployeeDomainModel

sealed class OnScreenState{
    object IDLE:OnScreenState()
    object LOADING:OnScreenState()
    data class SUCCESS(val user: List<EmployeeDomainModel>) : OnScreenState()
    data class ERROR(val error: String?) : OnScreenState()
}
