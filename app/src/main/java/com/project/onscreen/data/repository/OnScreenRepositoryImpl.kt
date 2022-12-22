package com.project.onscreen.data.repository

import com.project.onscreen.data.api.ApiService
import com.project.onscreen.data.db.OnScreenDB
import com.project.onscreen.data.mapper.Mapper
import com.project.onscreen.domain.model.AnimeDomainModel
import com.project.onscreen.domain.model.EmployeeDomainModel
import com.project.onscreen.domain.repository.OnScreenRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class OnScreenRepositoryImpl @Inject constructor(
    onScreenDB: OnScreenDB,
    val apiService: ApiService,
    val mapper: Mapper,
    val dispatcher: CoroutineDispatcher
) :
    OnScreenRepository {
    var dbDao = onScreenDB.employeesDao()
    var animeDao = onScreenDB.animesDao()

    override fun getEmployees(): Flow<List<EmployeeDomainModel>> {
        return flow {
            if (dbDao.getAllEmployees().isEmpty() == false) {
                emit(mapper.mapToEmployeeDomain(dbDao.getAllEmployees()))
            } else {
                val result = apiService.fetchEmployees()
                emit(mapper.mapToEmployeeDomain(result))
                dbDao.apply {
                    this.deleteAllEmployees()
                    this.insertEmployees(mapper.mapToEmployeeEntity(result))
                }
            }
        }.flowOn(dispatcher)
    }

    override fun getAnimeList(title: String?): Flow<List<AnimeDomainModel>> {
        return flow {
            if (!animeDao.getAllAnimes().isEmpty() && animeDao.getAnimeByName(title) != null) {
                emit(mapper.mapToAnimeDomain(animeDao.getAllAnimes()))
            } else {
                val result = apiService.fetchAnimeUsingTitle(title)
                animeDao.apply {
                    this.deleteAllAnimes()
                    this.insertAnimes(mapper.mapToAnimeEntity(result))

                }
                emit(mapper.mapToAnimeDomain(result))
            }
        }.flowOn(dispatcher)

    }


}