package com.project.onscreen.data.api

import com.project.onscreen.data.response.AnimeDto
import com.project.onscreen.data.response.EmployeeListDto

class ApiHelperImpl(val apiService: ApiService) :ApiHelper {
    override suspend fun getEmployees(): ArrayList<EmployeeListDto> {
        return apiService.fetchEmployees()
    }

    override suspend fun getAnimeQuotes(title:String?): ArrayList<AnimeDto> {
        return apiService.fetchAnimeUsingTitle(title)
    }

}
