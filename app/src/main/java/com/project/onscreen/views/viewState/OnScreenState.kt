package com.project.onscreen.views.viewState
import com.project.onscreen.domain.model.Employee

sealed class OnScreenState{
    object IDLE:OnScreenState()
    object LOADING:OnScreenState()
    data class SUCCESS(val user: List<Employee>?) : OnScreenState()
    data class ERROR(val error: String?) : OnScreenState()
}
