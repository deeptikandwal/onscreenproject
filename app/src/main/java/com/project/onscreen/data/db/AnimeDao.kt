package com.project.onscreen.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.onscreen.data.model.Anime

@Dao
interface  AnimeDao {
    @Query("SELECT * FROM Animes")
    fun getAllAnimes(): List<Anime>

    @Query("SELECT * FROM Animes where anime like :name")
    fun getAnimeByName(name:String?): Anime?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimes(employees: ArrayList<Anime>)

    @Query("DELETE FROM Animes")
    suspend fun deleteAllAnimes()
}