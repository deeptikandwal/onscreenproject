package com.project.onscreen.data.domain.usecase

import com.project.onscreen.data.api.ApiHelper
import com.project.onscreen.data.model.Employee
import com.project.onscreen.data.repository.MainRepository

class GetEmployeesUseCaseImpl(
    private val apiHelper: ApiHelper,
    private val mainRepository: MainRepository
) : GetEmployeesUseCase {
    override suspend fun getEmployees(): List<Employee> {
        if (mainRepository.getEmployeesList().isEmpty()) {
            val result = apiHelper.getEmployees()
            result.set(0, Employee(name = "Jonathan", email = "jonas134@gmail.com", id = 0, like = "Like-Animes", avatar = "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/1039.jpg"))
            mainRepository.saveEmployees(result)
            return result
        }
        return mainRepository.getEmployeesList()
    }

}