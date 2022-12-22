package com.project.onscreen.data.response
import com.google.gson.annotations.SerializedName
data class AnimeDto(
    @SerializedName("id")
    val id:Int?=null,
    @SerializedName("anime")
    val anime: String?=null,
    @SerializedName("character")
    val character: String?=null,
    @SerializedName("quote")
    val quote: String?=null
)