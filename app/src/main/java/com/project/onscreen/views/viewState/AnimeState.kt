package com.project.onscreen.views.viewState

import com.project.onscreen.domain.model.Anime

sealed class AnimeState{
    object IDLE:AnimeState()
    object LOADING:AnimeState()
    data class SUCCESS(val animes: List<Anime>?) : AnimeState()
    data class ERROR(val error: String?) : AnimeState()
}
