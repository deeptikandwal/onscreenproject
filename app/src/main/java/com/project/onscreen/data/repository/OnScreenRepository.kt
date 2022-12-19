package com.project.onscreen.data.repository

import com.project.onscreen.data.model.Anime
import com.project.onscreen.data.model.EmployeeList

interface OnScreenRepository {
   suspend fun getEmployees(): ArrayList<EmployeeList>?
   suspend fun getAnimeList(title: String?): ArrayList<Anime>?
}