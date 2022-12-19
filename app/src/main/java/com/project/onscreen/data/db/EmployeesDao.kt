package com.project.onscreen.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.onscreen.data.model.EmployeeList

@Dao
interface EmployeesDao {
    @Query("SELECT * FROM Employees")
    fun getAllEmployees(): List<EmployeeList>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployees(employees: ArrayList<EmployeeList>)

    @Query("DELETE FROM Employees")
    suspend fun deleteAllEmployees()
}
