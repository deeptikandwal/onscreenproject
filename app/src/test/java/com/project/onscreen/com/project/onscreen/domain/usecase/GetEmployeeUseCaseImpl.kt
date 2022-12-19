package com.project.onscreen.com.project.onscreen.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.onscreen.data.api.ApiHelper
import com.project.onscreen.data.model.EmployeeList
import com.project.onscreen.data.repository.OnScreenRepository
import com.project.onscreen.domain.usecase.GetEmployeesUseCaseImpl
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

class GetEmployeeUseCaseImpl {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    val dispatcher = TestCoroutineDispatcher()
    lateinit var getEmployeesUseCaseImpl: GetEmployeesUseCaseImpl
    @Mock
    lateinit var apiHelper: ApiHelper
    @Mock
    lateinit var onScreenRepository: OnScreenRepository
    @Mock
    lateinit var mockList:ArrayList<EmployeeList>
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        getEmployeesUseCaseImpl= GetEmployeesUseCaseImpl(onScreenRepository)
    }

    @Test
    fun `get employees in case of success`()= runTest{
        getEmployeesUseCaseImpl.getEmployees()
        Mockito.verify(onScreenRepository,Mockito.times(1)).getEmployees()
    }

    @Test
    fun `get employees in case of failure`()= runTest{
        getEmployeesUseCaseImpl.getEmployees()
        Mockito.verify(onScreenRepository,Mockito.times(1)).getEmployees()
    }
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}