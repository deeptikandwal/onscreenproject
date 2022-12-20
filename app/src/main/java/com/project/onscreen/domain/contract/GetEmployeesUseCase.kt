package com.project.onscreen.domain.contract

import com.project.onscreen.data.response.EmployeeListDto
import com.project.onscreen.domain.model.Employee

interface GetEmployeesUseCase {
    suspend fun getEmployees(): List<Employee>?
    suspend fun convertToDomain(list: List<EmployeeListDto>):List<Employee>?

}