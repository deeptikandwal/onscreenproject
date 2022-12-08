package com.project.onscreen.data.repository

import com.project.onscreen.data.model.Anime
import com.project.onscreen.data.model.Employee

class MainRepositoryImpl : MainRepository {
    private var employeeList = arrayListOf<Employee>()
    private var animeList = arrayListOf<Anime>()
    private var query = ""
    override fun saveEmployees(list: ArrayList<Employee>) {
        employeeList.addAll(list)
    }

    override fun getEmployeesList(): List<Employee> {
        return employeeList
    }

    override suspend fun saveAnimes(list: ArrayList<Anime>) {
        animeList.addAll(list)
    }

    override suspend fun clearAnimes() {
        animeList.clear()
    }

    override suspend fun saveQuery(query: String) {
        this.query=query
    }

    override suspend fun getQuery(): String {
        return query
    }

    override suspend fun getAnimeList(): List<Anime> {
        return animeList
    }

}