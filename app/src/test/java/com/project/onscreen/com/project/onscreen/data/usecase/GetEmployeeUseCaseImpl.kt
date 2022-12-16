package com.project.onscreen.com.project.onscreen.data.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.onscreen.data.api.ApiHelper
import com.project.onscreen.data.model.Employee
import com.project.onscreen.data.repository.MainRepository
import com.project.onscreen.data.domain.usecase.GetEmployeesUseCase
import com.project.onscreen.data.domain.usecase.GetEmployeesUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
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
    lateinit var mainRepository: MainRepository
    @Mock
    lateinit var mockList:ArrayList<Employee>
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        getEmployeesUseCaseImpl= GetEmployeesUseCaseImpl(apiHelper,mainRepository)
    }

    @Test
    fun `get Employees when repository is empty`()= runTest{
        val arrayList= arrayListOf(Employee(1,"Jonas","jona@gmail.com"))
        Mockito.`when`(mainRepository.getEmployeesList()).thenReturn(mockList)
        Mockito.`when`(mockList.isEmpty()).thenReturn(true)
        Mockito.`when`(apiHelper.getEmployees()).thenReturn(arrayList)
        getEmployeesUseCaseImpl.getEmployees()
        Mockito.verify(mainRepository).saveEmployees(arrayList)
        Mockito.verify(mainRepository,Mockito.times(1)).getEmployeesList()
    }

    @Test
    fun `get Employees when repository is not empty`()= runTest{
        Mockito.`when`(mainRepository.getEmployeesList()).thenReturn(mockList)
        Mockito.`when`(mockList.isEmpty()).thenReturn(false)
        getEmployeesUseCaseImpl.getEmployees()
        Mockito.verify(mainRepository,Mockito.times(2)).getEmployeesList()
    }
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}