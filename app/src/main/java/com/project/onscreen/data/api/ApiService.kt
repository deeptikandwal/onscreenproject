package com.project.onscreen.data.api

import com.project.onscreen.data.response.AnimeDto
import com.project.onscreen.data.response.EmployeeListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
   @GET("users")
   suspend fun fetchEmployees():ArrayList<EmployeeListDto>

   @GET("api/quotes/anime")
   suspend fun fetchAnimeUsingTitle( @Query("title") title: String?):ArrayList<AnimeDto>

}