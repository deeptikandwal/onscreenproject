package com.project.onscreen.domain.usecase

import com.project.onscreen.data.model.Employee

interface GetEmployeesUseCase {
    suspend fun getEmployees():List<Employee>
}