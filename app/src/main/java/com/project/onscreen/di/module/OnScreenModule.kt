package com.project.onscreen.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.project.onscreen.BuildConfig
import com.project.onscreen.data.utils.ApiConstants
import com.project.onscreen.data.api.ApiHelper
import com.project.onscreen.data.api.ApiHelperImpl
import com.project.onscreen.data.api.ApiService
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module
@InstallIn(ViewModelComponent::class)
class OnScreenModule {

    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()


    @Provides
    @Named(ApiConstants.BASE_URL)
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson: Gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(ApiConstants.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Named(ApiConstants.BASE_URL_ANIME)
    fun provideRetrofitAnime(okHttpClient: OkHttpClient): Retrofit {
        val gson: Gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(ApiConstants.BASE_URL_ANIME)
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Named(ApiConstants.BASE_URL)
    fun provideApiService(@Named(ApiConstants.BASE_URL) retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Named(ApiConstants.BASE_URL_ANIME)
    fun provideApiServiceAnime(@Named(ApiConstants.BASE_URL_ANIME) retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Named(ApiConstants.BASE_URL)
    fun provideApiHelper(@Named(ApiConstants.BASE_URL) apiService: ApiService): ApiHelper =
        ApiHelperImpl(apiService)

    @Provides
    @Named(ApiConstants.BASE_URL_ANIME)
    fun provideApiHelperAnime(@Named(ApiConstants.BASE_URL_ANIME) apiService: ApiService): ApiHelper =
        ApiHelperImpl(apiService)

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