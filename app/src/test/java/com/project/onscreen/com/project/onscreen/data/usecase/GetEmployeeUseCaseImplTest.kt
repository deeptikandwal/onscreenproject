package com.project.onscreen.com.project.onscreen.data.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.onscreen.data.api.ApiHelper
import com.project.onscreen.data.response.EmployeeListDto
import com.project.onscreen.domain.repository.OnScreenRepository
import com.project.onscreen.data.usecase.GetEmployeesUseCaseImpl
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
    val dispatcher = TestCoroutineDispatcher()
    lateinit var getEmployeesUseCaseImpl: GetEmployeesUseCaseImpl
    @Mock
    lateinit var apiHelper: ApiHelper
    @Mock
    lateinit var onScreenRepository: OnScreenRepository
    @Mock
    lateinit var mockList:ArrayList<EmployeeListDto>
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        getEmployeesUseCaseImpl= GetEmployeesUseCaseImpl(onScreenRepository)
    }

    @Test
    fun `get employees`()= runTest{
        getEmployeesUseCaseImpl.getEmployees()
        Mockito.verify(onScreenRepository,Mockito.times(1)).getEmployees()
    }

    @Test
    fun `convert to domain`()= runTest{
        val list = arrayListOf(EmployeeListDto(1, "Naruto", "lotto@gmail.com", ""))
        Assert.assertEquals(getEmployeesUseCaseImpl.convertToDomain(list).get(0).email,"lotto@gmail.com")
    }
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}