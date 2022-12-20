package com.project.onscreen.data.repository

import com.project.onscreen.data.response.AnimeDto
import com.project.onscreen.data.response.EmployeeListDto

interface OnScreenRepository {
   suspend fun getEmployees(): ArrayList<EmployeeListDto>?
   suspend fun getAnimeList(title: String?): ArrayList<AnimeDto>?
}