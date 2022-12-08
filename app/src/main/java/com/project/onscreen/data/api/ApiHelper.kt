package com.project.onscreen.data.api

import com.project.onscreen.data.model.Anime
import com.project.onscreen.data.model.Employee

interface ApiHelper {
    suspend fun getEmployees(): ArrayList<Employee>
    suspend fun getAnimeQuotes(title:String?): ArrayList<Anime>

}