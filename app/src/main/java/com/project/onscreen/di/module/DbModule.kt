package com.project.onscreen.di.module

import android.app.Application
import androidx.room.Room
import com.project.onscreen.data.db.OnScreenDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DbModule {
    @Singleton
    @Provides
    fun provideDatabase(app: Application): OnScreenDB =
        Room.databaseBuilder(app.applicationContext, OnScreenDB::class.java, "onscreen_database").allowMainThreadQueries()
            .build()
}