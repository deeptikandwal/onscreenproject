package com.project.onscreen.com.project.onscreen.views.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.onscreen.data.model.Anime
import com.project.onscreen.data.model.Employee
import com.project.onscreen.data.domain.usecase.GetAnimesUseCase
import com.project.onscreen.views.intent.OnScreenIntent
import com.project.onscreen.views.viewState.OnScreenState
import com.project.onscreen.views.viewmodel.AnimeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class AnimeViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    val dispatcher = TestCoroutineDispatcher()
    private lateinit var animeViewModel: AnimeViewModel

    @Mock
    lateinit var getAnimesUseCase: GetAnimesUseCase


    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.initMocks(this)
        animeViewModel = AnimeViewModel(getAnimesUseCase)
        animeViewModel.setUseCase(getAnimesUseCase)
    }

    @Test
    fun handleOperationSuccessTest(): Unit = runTest {
        val intentOnScreen = animeViewModel.intentOnScreen
        Mockito.`when`((getAnimesUseCase).getAnimes("null")).thenReturn(mutableListOf(Anime("null","jo")))
        intentOnScreen.send(OnScreenIntent.FetchAnimes)
        Mockito.verify(getAnimesUseCase).getAnimes("null")
        Assert.assertNotEquals(getAnimesUseCase.getAnimes("null")?.size ?: 0,0)
    }

    @Test(expected =retrofit2.HttpException::class )
    fun handleOperationFailTest(): Unit = runTest {
        val intentOnScreen = animeViewModel.intentOnScreen
        Mockito.`when`((getAnimesUseCase).getAnimes("null")).thenThrow(
            HttpException(
                Response.error<Any>(
                    409,
                    ResponseBody.create("plain/text".toMediaTypeOrNull(), "")
                )
            )
        )
        intentOnScreen.send(OnScreenIntent.FetchAnimes)
        Assert.assertEquals((getAnimesUseCase).getAnimes("null")?.size,0)

    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}