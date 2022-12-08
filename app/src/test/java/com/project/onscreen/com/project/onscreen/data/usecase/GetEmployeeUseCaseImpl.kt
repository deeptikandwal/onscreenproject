package com.project.onscreen.com.project.onscreen.data.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.onscreen.data.api.ApiHelper
import com.project.onscreen.data.repository.MainRepository
import com.project.onscreen.data.usecase.GetEmployeesUseCase
import com.project.onscreen.data.usecase.GetEmployeesUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)

class GetEmployeeUseCaseImpl {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    val dispatcher = TestCoroutineDispatcher()
    lateinit var getEmployeesUseCaseImpl: GetEmployeesUseCaseImpl
    @Mock
    lateinit var apiHelper: ApiHelper
    @Mock
    lateinit var mainRepository: MainRepository
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        getEmployeesUseCaseImpl=GetEmployeesUseCaseImpl(apiHelper,mainRepository)
    }

    @Test
    fun getEmployeesSuccessTest(){

    }
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}