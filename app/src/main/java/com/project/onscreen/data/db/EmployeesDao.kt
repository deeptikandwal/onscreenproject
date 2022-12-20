package com.project.onscreen.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.onscreen.data.response.EmployeeListDto
import com.project.onscreen.domain.model.Employee

@Dao
interface EmployeesDao {
    @Query("SELECT * FROM Employees")
    fun getAllEmployees(): List<EmployeeListDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployees(employees: ArrayList<EmployeeListDto>)

    @Query("DELETE FROM Employees")
    suspend fun deleteAllEmployees()
}
