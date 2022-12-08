package com.project.onscreen.data.usecase

import com.project.onscreen.data.model.Employee

interface GetEmployeesUseCase {
    suspend fun getEmployees():List<Employee>
}