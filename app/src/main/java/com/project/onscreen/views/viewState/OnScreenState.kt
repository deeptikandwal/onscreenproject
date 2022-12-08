package com.project.onscreen.views.viewState

import com.project.onscreen.data.model.Anime
import com.project.onscreen.data.model.Employee

sealed class OnScreenState{
    object IDLE:OnScreenState()
    object LOADING:OnScreenState()
    data class EMPLOYEES(val user: List<Employee>) : OnScreenState()
    data class Animes(val animes: List<Anime>?) : OnScreenState()
    data class ERROR(val error: String?) : OnScreenState()
}
