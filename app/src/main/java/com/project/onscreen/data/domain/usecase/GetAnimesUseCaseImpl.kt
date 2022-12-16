package com.project.onscreen.data.domain.usecase

import com.project.onscreen.data.api.ApiHelper
import com.project.onscreen.data.model.Anime
import com.project.onscreen.data.repository.MainRepository

class GetAnimesUseCaseImpl(
    private val apiHelper: ApiHelper,
    private val mainRepository: MainRepository?
) : GetAnimesUseCase {
    override suspend fun getAnimes(title: String?): List<Anime>? {
        if(mainRepository?.getAnimeList()?.isEmpty() == true || !mainRepository?.getQuery().equals(title)) {
            val result = apiHelper.getAnimeQuotes(title)
            mainRepository.also {
                it?.clearAnimes()
                it?.saveAnimes(result)
                if (title != null) {
                    it?.saveQuery(title)
                }
            }
            return result
        }
       return mainRepository?.getAnimeList()
    }
}