package com.project.onscreen.com.project.onscreen.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.onscreen.data.response.EmployeeListDto
import com.project.onscreen.domain.repository.OnScreenRepository
import com.project.onscreen.domain.usecase.GetEmployeesUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)

class GetEmployeeUseCaseImplTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var getEmployeesUseCaseImpl: GetEmployeesUseCaseImpl
    @Mock
    lateinit var onScreenRepository: OnScreenRepository
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        getEmployeesUseCaseImpl= GetEmployeesUseCaseImpl(onScreenRepository)
    }

    @Test
    fun `get employees`()= runTest{
        getEmployeesUseCaseImpl.getEmployees()
        Mockito.verify(onScreenRepository,Mockito.times(1)).getEmployees()
    }

}