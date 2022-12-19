package com.project.onscreen.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.onscreen.data.model.Anime
import com.project.onscreen.data.model.EmployeeList

@Database(entities = [EmployeeList::class,Anime::class], version = 1, exportSchema = false)
abstract class OnScreenDB: RoomDatabase() {
    abstract fun employeesDao(): EmployeesDao
    abstract fun animesDao(): AnimeDao

}