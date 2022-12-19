package com.project.onscreen.domain.contract

import com.project.onscreen.data.model.EmployeeList

interface GetEmployeesUseCase {
    suspend fun getEmployees(): ArrayList<EmployeeList>?
}