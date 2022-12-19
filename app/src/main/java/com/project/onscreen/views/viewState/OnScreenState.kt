package com.project.onscreen.views.viewState
import com.project.onscreen.data.model.EmployeeList

sealed class OnScreenState{
    object IDLE:OnScreenState()
    object LOADING:OnScreenState()
    data class SUCCESS(val user: List<EmployeeList>?) : OnScreenState()
    data class ERROR(val error: String?) : OnScreenState()
}
