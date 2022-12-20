package com.project.onscreen.domain.contract

import com.project.onscreen.data.response.AnimeDto
import com.project.onscreen.domain.model.Anime

interface GetAnimesUseCase {
    suspend fun getAnimes(title: String?):List<Anime>?
    suspend fun convertToDomain(list: List<AnimeDto>):List<Anime>?
}