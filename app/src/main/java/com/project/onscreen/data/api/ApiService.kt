package com.project.onscreen.data.api

import com.project.onscreen.data.model.Anime
import com.project.onscreen.data.model.Employee
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
   @GET("users")
   suspend fun fetchEmployees():ArrayList<Employee>

   @GET("api/quotes/anime")
   suspend fun fetchAnimeUsingTitle( @Query("title") title: String?):ArrayList<Anime>

}