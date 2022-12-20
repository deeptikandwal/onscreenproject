package com.project.onscreen.domain.usecase
import com.project.onscreen.domain.contract.GetAnimesUseCase
import com.project.onscreen.data.repository.OnScreenRepository
import com.project.onscreen.data.response.AnimeDto
import com.project.onscreen.domain.model.Anime

class GetAnimesUseCaseImpl(
    private val onScreenRepository: OnScreenRepository
) : GetAnimesUseCase {
    override suspend fun getAnimes(title: String?): List<Anime>? {
       return onScreenRepository.getAnimeList(title)?.let { convertToDomain(it) }
    }

    override suspend fun convertToDomain(list: List<AnimeDto>): List<Anime> =list.map { Anime(it.anime,it.character,it.quote) }.toMutableList()
}