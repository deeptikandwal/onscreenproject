package com.project.onscreen.views.viewState

import com.project.onscreen.domain.model.AnimeDomainModel

sealed class AnimeState{
    object IDLE:AnimeState()
    object LOADING:AnimeState()
    data class SUCCESS(val animes: List<AnimeDomainModel>) : AnimeState()
    data class ERROR(val error: String?) : AnimeState()
}
