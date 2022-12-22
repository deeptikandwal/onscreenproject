package com.project.onscreen.com.project.onscreen.views.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.onscreen.domain.usecase.GetAnimesUseCaseImpl
import com.project.onscreen.domain.model.AnimeDomainModel
import com.project.onscreen.views.intent.OnScreenIntent
import com.project.onscreen.views.viewmodel.AnimeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class AnimeViewModelTest {
    @Mock
    private lateinit var getAnimesUseCase: GetAnimesUseCaseImpl

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    val dispatcher = TestCoroutineDispatcher()
    private lateinit var animeViewModel: AnimeViewModel

    @Mock
    lateinit var intentOnScreen: Channel<OnScreenIntent>


    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.initMocks(this)
        animeViewModel = AnimeViewModel(getAnimesUseCase)
        animeViewModel.intentOnScreen = intentOnScreen
        animeViewModel.getAnimesUseCase = getAnimesUseCase
    }

    @Test
    fun handleOperationSuccessTest(): Unit = runTest(dispatcher) {
        intentOnScreen.send(OnScreenIntent.FetchAnimes)
        Mockito.`when`(getAnimesUseCase.getAnimes("Naruto")).thenReturn(flowOf( listOf(
            AnimeDomainModel(1,"Naruto","pain")
        )))

        val animeList=getAnimesUseCase.getAnimes("Naruto").flatMapConcat { it.asFlow()}.toList()
        Assert.assertNotEquals(animeList.size, 0)
        Assert.assertEquals(animeList.get(0).anime, "Naruto")
    }

    @Test(expected = retrofit2.HttpException::class)
    fun handleOperationFailTest(): Unit = runTest(dispatcher) {
        Mockito.`when`((getAnimesUseCase).getAnimes("null")).thenThrow(
            HttpException(
                Response.error<Any>(
                    409,
                    ResponseBody.create("plain/text".toMediaTypeOrNull(), "")
                )
            )
        )
        Assert.assertEquals(getAnimesUseCase.getAnimes("null").flatMapConcat { it.asFlow()}.toList().size, 0)

    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}