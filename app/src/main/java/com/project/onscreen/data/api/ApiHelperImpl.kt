package com.project.onscreen.data.api

import com.project.onscreen.data.model.Anime
import com.project.onscreen.data.model.Employee

class ApiHelperImpl(val apiService: ApiService) :ApiHelper {
    override suspend fun getEmployees(): ArrayList<Employee> {
        return apiService.fetchEmployees()
    }

    override suspend fun getAnimeQuotes(title:String?): ArrayList<Anime> {
        return apiService.fetchAnimeUsingTitle(title)
    }

}
