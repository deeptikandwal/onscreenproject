package com.project.onscreen.di.module

import com.project.onscreen.data.api.ApiService
import com.project.onscreen.data.utils.ApiConstants
import com.project.onscreen.data.db.OnScreenDB
import com.project.onscreen.data.mapper.Mapper
import com.project.onscreen.domain.repository.OnScreenRepository
import com.project.onscreen.data.repository.OnScreenRepositoryImpl
import com.project.onscreen.domain.usecase.GetAnimesUseCaseImpl
import com.project.onscreen.domain.usecase.GetEmployeesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named


@Module
@InstallIn(ViewModelComponent::class)
class OnScreenModule {

    @Provides
    @Named(ApiConstants.BASE_URL)
    fun provideUseCase(@Named(ApiConstants.BASE_URL) onScreenRepository: OnScreenRepository)= GetEmployeesUseCaseImpl(onScreenRepository)

    @Provides
    @Named(ApiConstants.BASE_URL_ANIME)
    fun provideUseCaseAnime(@Named(ApiConstants.BASE_URL_ANIME) onScreenRepository: OnScreenRepository)= GetAnimesUseCaseImpl(onScreenRepository)

    @Provides
    @Named(ApiConstants.BASE_URL)
    fun provideRepository(
        onScreenDB: OnScreenDB,
        @Named(ApiConstants.BASE_URL) apiService: ApiService,mapper: Mapper,dispatcher: CoroutineDispatcher
    ): OnScreenRepository = OnScreenRepositoryImpl(onScreenDB, apiService,mapper,dispatcher)

    @Provides
    @Named(ApiConstants.BASE_URL_ANIME)
    fun provideRepositoryAnime(
        onScreenDB: OnScreenDB,
        @Named(ApiConstants.BASE_URL_ANIME) apiService: ApiService,mapper: Mapper,dispatcher: CoroutineDispatcher
    ): OnScreenRepository = OnScreenRepositoryImpl(onScreenDB, apiService, mapper,dispatcher)

    @Provides
    fun providerMapper()=Mapper()

}