package com.project.onscreen.domain.repository

import com.project.onscreen.domain.model.AnimeDomainModel
import com.project.onscreen.domain.model.EmployeeDomainModel
import kotlinx.coroutines.flow.Flow

interface OnScreenRepository {
    fun getEmployees(): Flow<List<EmployeeDomainModel>>
    fun getAnimeList(title: String?): Flow<List<AnimeDomainModel>>
}