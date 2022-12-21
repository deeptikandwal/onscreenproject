package com.project.onscreen.com.project.onscreen.views.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.onscreen.domain.usecase.GetEmployeesUseCase
import com.project.onscreen.domain.model.Employee
import com.project.onscreen.views.intent.OnScreenIntent
import com.project.onscreen.views.viewmodel.HomeScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
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

@ExperimentalCoroutinesApi
class HomeScreenViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    val dispatcher = TestCoroutineDispatcher()
    lateinit var homeScreenViewModel: HomeScreenViewModel

    @Mock
    lateinit var getEmployeesUseCase: GetEmployeesUseCase

    @Mock
    lateinit var intentOnScreen: Channel<OnScreenIntent>

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.initMocks(this)
        homeScreenViewModel = HomeScreenViewModel(getEmployeesUseCase)
        homeScreenViewModel.intentOnScreen = intentOnScreen
        homeScreenViewModel.getEmployeesUseCase = getEmployeesUseCase
    }

    @Test
    fun handleOperationSuccessTest(): Unit = runTest {
        Mockito.`when`(getEmployeesUseCase.getEmployees()).thenReturn(arrayListOf(Employee("Jones","jonas@gmail.com","")))
        Assert.assertNotEquals(getEmployeesUseCase.getEmployees()?.size, 0)
        Assert.assertEquals(getEmployeesUseCase.getEmployees()?.get(0)?.name, "Jones")
    }

    @Test(expected = retrofit2.HttpException::class)
    fun handleOperationFailTest(): Unit = runTest {
        Mockito.`when`(getEmployeesUseCase.getEmployees()).thenThrow(
            HttpException(
                Response.error<Any>(
                    409,
                    ResponseBody.create("plain/text".toMediaTypeOrNull(), "")
                )
            )
        )
        Assert.assertEquals(getEmployeesUseCase.getEmployees()?.size, 0)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}