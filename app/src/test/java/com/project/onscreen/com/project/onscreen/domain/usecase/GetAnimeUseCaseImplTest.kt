package com.project.onscreen.com.project.onscreen.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.onscreen.data.api.ApiHelper
import com.project.onscreen.data.model.Anime
import com.project.onscreen.data.repository.MainRepository
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
    lateinit var apiHelper: ApiHelper
    @Mock
    lateinit var mainRepository: MainRepository
    @Mock
    lateinit var mockList:ArrayList<Anime>
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        getAnimesUseCaseImpl= GetAnimesUseCaseImpl(apiHelper,mainRepository)
    }

    @Test
    fun `get Employees when repository is empty`()= runTest{
        val arrayList= arrayListOf(Anime())
        Mockito.`when`(mainRepository.getAnimeList()).thenReturn(mockList)
        Mockito.`when`(mockList.isEmpty()).thenReturn(true)
        Mockito.`when`(mainRepository.getQuery()).thenReturn("bleach")
        Mockito.`when`(apiHelper.getAnimeQuotes("Naruto")).thenReturn(arrayList)
        getAnimesUseCaseImpl.getAnimes("Naruto")
        Mockito.verify(mainRepository).saveAnimes(arrayList)
        Mockito.verify(mainRepository).clearAnimes()
        Mockito.verify(mainRepository,Mockito.times(1)).getAnimeList()
        Mockito.verify(mainRepository,Mockito.times(1)).saveQuery("Naruto")
    }

    @Test
    fun `get Employees when repository is not empty`()= runTest{
        Mockito.`when`(mainRepository.getAnimeList()).thenReturn(mockList)
        Mockito.`when`(mockList.isEmpty()).thenReturn(false)
        Mockito.`when`(mainRepository.getQuery()).thenReturn("Naruto")
        getAnimesUseCaseImpl.getAnimes("Naruto")
        Mockito.verify(mainRepository,Mockito.times(2)).getAnimeList()
    }
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}