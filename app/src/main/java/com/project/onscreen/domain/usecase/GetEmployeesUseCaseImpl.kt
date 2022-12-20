package com.project.onscreen.domain.usecase

import com.project.onscreen.data.response.EmployeeListDto
import com.project.onscreen.domain.contract.GetEmployeesUseCase
import com.project.onscreen.data.repository.OnScreenRepository
import com.project.onscreen.domain.model.Employee

class GetEmployeesUseCaseImpl(
    private val onScreenRepository: OnScreenRepository
) : GetEmployeesUseCase {
    override suspend fun getEmployees(): List<Employee>? {
        return onScreenRepository.getEmployees()?.let { convertToDomain(it) }
    }

    override suspend fun convertToDomain(list: List<EmployeeListDto>): List<Employee> =list.map { Employee(it.name,it.email,it.avatar) }

}