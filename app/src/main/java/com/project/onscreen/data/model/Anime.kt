package com.project.onscreen.data.model

import com.google.gson.annotations.SerializedName
data class Anime(
    @SerializedName("anime")
    val anime: String = "",
    @SerializedName("character")
    val character: String = "",
    @SerializedName("quote")
    val quote: String = ""
)