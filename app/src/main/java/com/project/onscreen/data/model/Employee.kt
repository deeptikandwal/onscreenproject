package com.project.onscreen.data.model

import com.google.gson.annotations.SerializedName


data class Employee(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("avatar")
    val avatar: String = "",
    @SerializedName("like")
    val like: String = "Like-Sports"
)