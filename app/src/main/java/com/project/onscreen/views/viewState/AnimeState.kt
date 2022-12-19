package com.project.onscreen.views.viewState

import com.project.onscreen.data.model.Anime
import com.project.onscreen.data.model.EmployeeList

sealed class AnimeState{
    object IDLE:AnimeState()
    object LOADING:AnimeState()
    data class SUCCESS(val animes: List<Anime>?) : AnimeState()
    data class ERROR(val error: String?) : AnimeState()
}
