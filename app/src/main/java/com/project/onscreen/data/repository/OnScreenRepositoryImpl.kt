package com.project.onscreen.data.repository

import androidx.room.withTransaction
import com.project.onscreen.data.api.ApiHelper
import com.project.onscreen.data.model.EmployeeList
import com.project.onscreen.data.db.OnScreenDB
import com.project.onscreen.data.model.Anime
import javax.inject.Inject

class OnScreenRepositoryImpl @Inject constructor(val onScreenDB: OnScreenDB, val apiHelper: ApiHelper) : OnScreenRepository {
    private val dbDao = onScreenDB.employeesDao()
    private val animeDao = onScreenDB.animesDao()
    override suspend fun getEmployees(): ArrayList<EmployeeList>? {
        return getEmployeesFromDB()
    }

    override suspend fun getEmployeesFromDB(): ArrayList<EmployeeList>? {
        if(dbDao.getAllEmployees().isEmpty()==false){
            return dbDao.getAllEmployees() as ArrayList<EmployeeList>
        }
        else{
            val result=apiHelper.getEmployees()
            onScreenDB.withTransaction {
                dbDao.apply {
                    this.deleteAllEmployees()
                    this.insertEmployees(result)
                }
            }
            return result
        }
    }

    override suspend fun getAnimeList(title: String?): ArrayList<Anime>? {
        return getAnimeListFromDB(title)
    }

    override suspend fun getAnimeListFromDB(title: String?): ArrayList<Anime>? {
        if(!animeDao.getAllAnimes().isEmpty() && animeDao.getAnimeByName(title)!=null){
            return animeDao.getAllAnimes() as ArrayList<Anime>
        }
        else{
            val result=apiHelper.getAnimeQuotes(title)
            onScreenDB.withTransaction {
                animeDao.apply {
                    this.deleteAllAnimes()
                    this.insertAnimes(result)
                }
            }
            return result
        }
    }

}