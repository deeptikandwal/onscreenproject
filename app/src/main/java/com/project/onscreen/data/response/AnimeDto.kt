package com.project.onscreen.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "Animes")
data class AnimeDto(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    @SerializedName("anime")
    val anime: String? = null,
    @SerializedName("character")
    val character: String = "",
    @SerializedName("quote")
    val quote: String = ""
)