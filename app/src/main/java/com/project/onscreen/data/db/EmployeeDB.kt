package com.project.onscreen.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.onscreen.data.response.AnimeDto
import com.project.onscreen.data.response.EmployeeListDto

@Database(entities = [EmployeeListDto::class, AnimeDto::class], version = 1, exportSchema = false)
abstract class OnScreenDB: RoomDatabase() {
    abstract fun employeesDao(): EmployeesDao
    abstract fun animesDao(): AnimeDao

}