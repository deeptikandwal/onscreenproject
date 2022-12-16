package com.project.onscreen.com.project.onscreen.views.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.onscreen.data.model.Employee
import com.project.onscreen.domain.usecase.GetEmployeesUseCase
import com.project.onscreen.views.intent.OnScreenIntent
import com.project.onscreen.views.viewmodel.HomeScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
class HomeScreenViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    val dispatcher = TestCoroutineDispatcher()
    lateinit var homeScreenViewModel: HomeScreenViewModel

    @Mock
    lateinit var getEmployeesUseCase: GetEmployeesUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.initMocks(this)
        homeScreenViewModel = HomeScreenViewModel(getEmployeesUseCase)
    }

    @Test
    fun handleOperationSuccessTest(): Unit = runTest {
        val intentOnScreen = homeScreenViewModel.intentOnScreen
        Mockito.`when`(getEmployeesUseCase.getEmployees()).thenReturn(mutableListOf(Employee(0,"jo")))
        intentOnScreen.send(OnScreenIntent.FetchEmployees)
        Mockito.verify(getEmployeesUseCase).getEmployees()
        Assert.assertNotEquals(getEmployeesUseCase.getEmployees().size,0)
    }

    @Test(expected =retrofit2.HttpException::class )
    fun handleOperationFailTest(): Unit = runTest {
        val intentOnScreen = homeScreenViewModel.intentOnScreen
        Mockito.`when`(getEmployeesUseCase.getEmployees()).thenThrow(
            HttpException(
                Response.error<Any>(
                    409,
                    ResponseBody.create("plain/text".toMediaTypeOrNull(), "")
                )
            )
        )
        intentOnScreen.send(OnScreenIntent.FetchEmployees)
        Assert.assertEquals(getEmployeesUseCase.getEmployees().size,0)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}