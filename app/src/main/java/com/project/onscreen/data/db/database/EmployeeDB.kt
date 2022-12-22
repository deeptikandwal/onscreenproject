package com.project.onscreen.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.onscreen.data.db.dao.AnimeDao
import com.project.onscreen.data.db.dao.EmployeesDao
import com.project.onscreen.data.db.entity.AnimeEntity
import com.project.onscreen.data.db.entity.EmployeeEntity

@Database(entities = [AnimeEntity::class, EmployeeEntity::class], version = 1, exportSchema = false)
abstract class OnScreenDB: RoomDatabase() {
    abstract fun employeesDao(): EmployeesDao
    abstract fun animesDao(): AnimeDao
}