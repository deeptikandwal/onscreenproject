package com.project.onscreen.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.onscreen.data.response.AnimeDto
import com.project.onscreen.domain.model.Anime

@Dao
interface  AnimeDao {
    @Query("SELECT * FROM Animes")
    fun getAllAnimes(): List<AnimeDto>

    @Query("SELECT * FROM Animes where anime like :name")
    fun getAnimeByName(name:String?): AnimeDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimes(employees: ArrayList<AnimeDto>)

    @Query("DELETE FROM Animes")
    suspend fun deleteAllAnimes()
}