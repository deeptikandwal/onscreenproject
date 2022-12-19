package com.project.onscreen.data.api

import com.project.onscreen.data.model.Anime
import com.project.onscreen.data.model.EmployeeList

interface ApiHelper {
    suspend fun getEmployees(): ArrayList<EmployeeList>
    suspend fun getAnimeQuotes(title:String?): ArrayList<Anime>

}