package com.project.onscreen.com.project.onscreen.domain.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.onscreen.data.api.ApiHelper
import com.project.onscreen.data.db.AnimeDao
import com.project.onscreen.data.db.EmployeesDao
import com.project.onscreen.data.db.OnScreenDB
import com.project.onscreen.data.response.AnimeDto
import com.project.onscreen.data.response.EmployeeListDto
import com.project.onscreen.data.repository.OnScreenRepositoryImpl
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
class OnScreenRepositoryImplTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    val dispatcher = TestCoroutineDispatcher()

    lateinit var onScreenRepositoryImpl: OnScreenRepositoryImpl
    @Mock
    lateinit var onScreenDB: OnScreenDB
    @Mock
    lateinit var apiHelper: ApiHelper
    @Mock
    lateinit var dbDao:EmployeesDao
    @Mock
    lateinit var animeDao: AnimeDao
    @Mock
    lateinit var mockList:ArrayList<EmployeeListDto>
    @Mock
    lateinit var mAnimeList:ArrayList<AnimeDto>
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        onScreenRepositoryImpl = OnScreenRepositoryImpl(onScreenDB,apiHelper)
    }

    @Test
    fun `get Employees from db`() = runTest{
        onScreenRepositoryImpl.dbDao=dbDao
        Mockito.`when`(dbDao.getAllEmployees()).thenReturn(mockList)
        Mockito.`when`(mockList.isEmpty()).thenReturn(false)
        onScreenRepositoryImpl.getEmployees()
        Mockito.verify(dbDao,Mockito.times(2)).getAllEmployees()
    }

    @Test
    fun `get Animes from db`() = runTest{
        onScreenRepositoryImpl.animeDao=animeDao
        Mockito.`when`(animeDao.getAllAnimes()).thenReturn(mAnimeList)
        Mockito.`when`(animeDao.getAnimeByName(null)).thenReturn(AnimeDto())
        Mockito.`when`(mAnimeList.isEmpty()).thenReturn(false)
        onScreenRepositoryImpl.getAnimeList(null)
        Mockito.verify(animeDao,Mockito.times(2)).getAllAnimes()
    }

    @Test
    fun `get Animes from api`() = runTest{
        onScreenRepositoryImpl.animeDao=animeDao
        val list= arrayListOf<AnimeDto>()
        Mockito.`when`(animeDao.getAllAnimes()).thenReturn(mAnimeList)
        Mockito.`when`(animeDao.getAnimeByName(null)).thenReturn(AnimeDto())
        Mockito.`when`(apiHelper.getAnimeQuotes(null)).thenReturn(list)
        Mockito.`when`(mAnimeList.isEmpty()).thenReturn(true)
        onScreenRepositoryImpl.getAnimeList(null)
        Mockito.verify(animeDao,Mockito.times(1)).getAllAnimes()
        Mockito.verify(animeDao,Mockito.times(1)).insertAnimes(list)
    }

    @Test
    fun `get Employees from api`() = runTest{
        onScreenRepositoryImpl.dbDao=dbDao
        val list= arrayListOf<EmployeeListDto>()
        Mockito.`when`(dbDao.getAllEmployees()).thenReturn(mockList)
        Mockito.`when`(mockList.isEmpty()).thenReturn(true)
        Mockito.`when`(apiHelper.getEmployees()).thenReturn(list)
        onScreenRepositoryImpl.getEmployees()
        Mockito.verify(dbDao,Mockito.times(1)).getAllEmployees()
        Mockito.verify(apiHelper,Mockito.times(1)).getEmployees()
        Mockito.verify(dbDao,Mockito.times(1)).insertEmployees(list)
    }


    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}