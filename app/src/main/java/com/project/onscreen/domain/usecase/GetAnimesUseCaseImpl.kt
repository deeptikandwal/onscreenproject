package com.project.onscreen.domain.usecase
import com.project.onscreen.domain.repository.OnScreenRepository
import com.project.onscreen.domain.model.AnimeDomainModel
import kotlinx.coroutines.flow.Flow

class GetAnimesUseCaseImpl(private val onScreenRepository: OnScreenRepository)  {
      fun getAnimes(title: String?): Flow<List<AnimeDomainModel>> {
       return onScreenRepository.getAnimeList(title)
    }
}