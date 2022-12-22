package com.project.onscreen.com.project.onscreen.data.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.onscreen.domain.repository.OnScreenRepository
import com.project.onscreen.data.response.AnimeDto
import com.project.onscreen.data.usecase.GetAnimesUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)

class GetAnimeUseCaseImplTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var getAnimesUseCaseImpl: GetAnimesUseCaseImpl

    @Mock
    lateinit var onScreenRepository: OnScreenRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getAnimesUseCaseImpl = GetAnimesUseCaseImpl(onScreenRepository)
    }

    @Test
    fun getAnimesTest() = runTest {
        getAnimesUseCaseImpl.getAnimes("Naruto")
        Mockito.verify(onScreenRepository, Mockito.times(1)).getAnimeList("Naruto")
    }

}