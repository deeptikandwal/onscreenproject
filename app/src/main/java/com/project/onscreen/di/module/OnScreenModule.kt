package com.project.onscreen.di.module

import com.project.onscreen.data.utils.ApiConstants
import com.project.onscreen.data.api.ApiHelper
import com.project.onscreen.data.db.OnScreenDB
import com.project.onscreen.domain.repository.OnScreenRepository
import com.project.onscreen.data.repository.OnScreenRepositoryImpl
import com.project.onscreen.domain.usecase.GetAnimesUseCase
import com.project.onscreen.data.usecase.GetAnimesUseCaseImpl
import com.project.onscreen.domain.usecase.GetEmployeesUseCase
import com.project.onscreen.data.usecase.GetEmployeesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named


@Module
@InstallIn(ViewModelComponent::class)
class OnScreenModule {

    @Provides
    @Named(ApiConstants.BASE_URL)
    fun provideUseCase(@Named(ApiConstants.BASE_URL) onScreenRepository: OnScreenRepository): GetEmployeesUseCase =
        GetEmployeesUseCaseImpl(onScreenRepository)

    @Provides
    @Named(ApiConstants.BASE_URL_ANIME)
    fun provideUseCaseAnime(@Named(ApiConstants.BASE_URL_ANIME) onScreenRepository: OnScreenRepository): GetAnimesUseCase =
        GetAnimesUseCaseImpl(onScreenRepository)

    @Provides
    @Named(ApiConstants.BASE_URL)
    fun provideRepository(
        onScreenDB: OnScreenDB,
        @Named(ApiConstants.BASE_URL) apiHelper: ApiHelper
    ): OnScreenRepository = OnScreenRepositoryImpl(onScreenDB, apiHelper)

    @Provides
    @Named(ApiConstants.BASE_URL_ANIME)
    fun provideRepositoryAnime(
        onScreenDB: OnScreenDB,
        @Named(ApiConstants.BASE_URL_ANIME) apiHelper: ApiHelper
    ): OnScreenRepository = OnScreenRepositoryImpl(onScreenDB, apiHelper)

}