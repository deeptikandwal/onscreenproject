package com.project.onscreen.com.project.onscreen.data.api

import com.google.gson.Gson
import com.project.onscreen.data.api.ApiHelperImpl
import com.project.onscreen.data.api.ApiService
import com.project.onscreen.data.response.AnimeDto
import com.project.onscreen.data.response.EmployeeListDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@OptIn(ExperimentalCoroutinesApi::class)
class ApiHelperImplTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService
    private lateinit var apiHelperImpl: ApiHelperImpl

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
        apiHelperImpl = ApiHelperImpl(apiService)
    }

    @Test
    fun `get employees with http code 200`() = runTest {
        val employees = arrayListOf(
            EmployeeListDto(1, "Jones", "jonas34@gmail.com", ""),
            EmployeeListDto(2, "Samantha", "sam@gmail.com", "")
        )

        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(employees))
        mockWebServer.enqueue(expectedResponse)
        val actualResponse = apiHelperImpl.getEmployees()
        Assert.assertEquals(actualResponse.get(0).email, "jonas34@gmail.com")
    }

    @Test(expected =retrofit2.HttpException::class )
    fun `get employees with http code 400`() = runTest {
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
            .setBody(Gson().toJson(null))
        mockWebServer.enqueue(expectedResponse)
        val actualResponse = apiHelperImpl.getEmployees()
        Assert.assertEquals(actualResponse.size, 0)

    }

    @Test(expected =retrofit2.HttpException::class )
    fun `get anime quotes with http code 404`() = runTest {
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
        mockWebServer.enqueue(expectedResponse)
        val actualResponse = apiHelperImpl.getAnimeQuotes("bbbbbbb")
        Assert.assertEquals(actualResponse.size, 0)
    }

    @Test
    fun `get anime quotes with http code 200`() = runTest {
        val employees = arrayListOf(
            AnimeDto(1, "Naruto", "pain", "This is my nindo"),
        )

        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(employees))
        mockWebServer.enqueue(expectedResponse)
        val actualResponse = apiHelperImpl.getAnimeQuotes("Naruto")
        Assert.assertEquals(actualResponse.get(0).character, "pain")
    }
    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}