package com.project.onscreen.domain.usecase

import com.project.onscreen.data.model.EmployeeList
import com.project.onscreen.data.repository.OnScreenRepository

class GetEmployeesUseCaseImpl(
    private val onScreenRepository: OnScreenRepository
) : GetEmployeesUseCase {
    override suspend fun getEmployees(): ArrayList<EmployeeList>? {
            val result = onScreenRepository.getEmployees()
        return result
    }

}