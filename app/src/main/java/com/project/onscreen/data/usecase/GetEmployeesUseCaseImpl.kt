package com.project.onscreen.data.usecase

import com.project.onscreen.domain.model.EmployeeDomainModel
import com.project.onscreen.domain.repository.OnScreenRepository
import kotlinx.coroutines.flow.Flow

class GetEmployeesUseCaseImpl(private val onScreenRepository: OnScreenRepository) {
     fun getEmployees(): Flow<List<EmployeeDomainModel>> {
        return onScreenRepository.getEmployees()
    }

}