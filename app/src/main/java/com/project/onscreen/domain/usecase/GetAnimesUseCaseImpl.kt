package com.project.onscreen.domain.usecase
import com.project.onscreen.data.model.Anime
import com.project.onscreen.domain.contract.GetAnimesUseCase
import com.project.onscreen.data.repository.OnScreenRepository

class GetAnimesUseCaseImpl(
    private val onScreenRepository: OnScreenRepository
) : GetAnimesUseCase {
    override suspend fun getAnimes(title: String?): List<Anime>? {
       return onScreenRepository.getAnimeList(title)
    }
}