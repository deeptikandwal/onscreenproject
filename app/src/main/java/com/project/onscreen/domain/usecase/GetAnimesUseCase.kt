package com.project.onscreen.domain.usecase

import com.project.onscreen.data.model.Anime

interface GetAnimesUseCase {
    suspend fun getAnimes(title: String?):List<Anime>?
}