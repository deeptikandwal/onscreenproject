package com.project.onscreen.data.mapper

import com.project.onscreen.data.db.entity.AnimeEntity
import com.project.onscreen.data.db.entity.EmployeeEntity
import com.project.onscreen.data.response.AnimeDto
import com.project.onscreen.data.response.EmployeeListDto
import com.project.onscreen.domain.model.AnimeDomainModel
import com.project.onscreen.domain.model.EmployeeDomainModel
import kotlinx.coroutines.flow.*

 class Mapper {
     @JvmName("mapToEmployeeDomainFromDto")
     fun mapToEmployeeDomain(itemsdto:List<EmployeeListDto>): List<EmployeeDomainModel> = itemsdto.map { EmployeeDomainModel(it.id,it.name,it.email,it.avatar) }

     @JvmName("mapToEmployeeDomainFromEntity")
     fun mapToEmployeeDomain(itemsEntity: List<EmployeeEntity>): List<EmployeeDomainModel> = itemsEntity.map { EmployeeDomainModel(it.id?:0,it.name.orEmpty(),it.email.orEmpty(),it.avatar.orEmpty()) }

     fun mapToEmployeeEntity(itemsdto: List<EmployeeListDto>): List<EmployeeEntity> = itemsdto.map { EmployeeEntity(it.id,it.name,it.email,it.avatar) }

     @JvmName("mapToAnimeDomainFromDto")
     fun mapToAnimeDomain(itemsdto: List<AnimeDto>): List<AnimeDomainModel> = itemsdto.map { AnimeDomainModel(it.id?:0,it.anime.orEmpty().plus("-").plus(it.character.orEmpty()),it.quote.orEmpty()) }

     fun mapToAnimeEntity(itemsdto: List<AnimeDto>): List<AnimeEntity> = itemsdto.map { AnimeEntity(it.id,it.anime,it.character,it.quote) }

     @JvmName("mapToAnimeDomainFromEntity")
     fun mapToAnimeDomain(itemsEntity: List<AnimeEntity>): List<AnimeDomainModel> = itemsEntity.map { AnimeDomainModel(it.id?:0,it.anime.orEmpty().plus("-").plus(it.character.orEmpty()),it.quote.orEmpty()) }
}