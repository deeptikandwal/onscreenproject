package com.project.onscreen.domain.usecase

import com.project.onscreen.data.model.EmployeeList

interface GetEmployeesUseCase {
    suspend fun getEmployees(): ArrayList<EmployeeList>?
}