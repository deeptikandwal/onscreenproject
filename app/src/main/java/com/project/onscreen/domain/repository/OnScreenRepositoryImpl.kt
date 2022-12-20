package com.project.onscreen.domain.repository

import com.project.onscreen.data.api.ApiHelper
import com.project.onscreen.data.db.OnScreenDB
import com.project.onscreen.data.repository.OnScreenRepository
import com.project.onscreen.data.response.AnimeDto
import com.project.onscreen.data.response.EmployeeListDto
import javax.inject.Inject

class OnScreenRepositoryImpl @Inject constructor(
    onScreenDB: OnScreenDB,
    val apiHelper: ApiHelper
) :
    OnScreenRepository {
    var dbDao = onScreenDB.employeesDao()
    var animeDao = onScreenDB.animesDao()

    override suspend fun getEmployees(): ArrayList<EmployeeListDto>? {
        if (dbDao.getAllEmployees().isEmpty() == false) {
            return dbDao.getAllEmployees() as ArrayList<EmployeeListDto>
        } else {
            val result = apiHelper.getEmployees()
            dbDao.apply {
                this.deleteAllEmployees()
                this.insertEmployees(result)
            }

            return result
        }
    }


    override suspend fun getAnimeList(title: String?): ArrayList<AnimeDto>? {
        if (!animeDao.getAllAnimes().isEmpty() && animeDao.getAnimeByName(title) != null) {
            return animeDao.getAllAnimes() as ArrayList<AnimeDto>
        } else {
            val result = apiHelper.getAnimeQuotes(title)
            animeDao.apply {
                this.deleteAllAnimes()
                this.insertAnimes(result)

            }
            return result
        }
    }


}