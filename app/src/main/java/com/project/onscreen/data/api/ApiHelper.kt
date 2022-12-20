package com.project.onscreen.data.api

import com.project.onscreen.data.response.AnimeDto
import com.project.onscreen.data.response.EmployeeListDto

interface ApiHelper {
    suspend fun getEmployees(): ArrayList<EmployeeListDto>
    suspend fun getAnimeQuotes(title:String?): ArrayList<AnimeDto>

}