package com.project.onscreen.com.project.onscreen.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.onscreen.data.repository.OnScreenRepository
import com.project.onscreen.domain.usecase.GetAnimesUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)

class GetAnimeUseCaseImplTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    val dispatcher = TestCoroutineDispatcher()
    lateinit var getAnimesUseCaseImpl: GetAnimesUseCaseImpl
    @Mock
    lateinit var onScreenRepository: OnScreenRepository
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        getAnimesUseCaseImpl= GetAnimesUseCaseImpl(onScreenRepository)
    }

    @Test
    fun getAnimesTest()= runTest{
        getAnimesUseCaseImpl.getAnimes("Naruto")
        Mockito.verify(onScreenRepository,Mockito.times(1)).getAnimeList("Naruto")
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}