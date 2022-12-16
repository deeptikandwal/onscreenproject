package com.project.onscreen.data.repository

import com.project.onscreen.data.model.Anime
import com.project.onscreen.data.model.Employee

interface MainRepository {
    fun saveEmployees(list: ArrayList<Employee>)

    fun getEmployeesList(): List<Employee>

    suspend fun saveAnimes(list: ArrayList<Anime>)

    suspend fun clearAnimes()

    suspend fun saveQuery(query: String)

    suspend fun getQuery(): String

    suspend fun getAnimeList(): List<Anime>
}