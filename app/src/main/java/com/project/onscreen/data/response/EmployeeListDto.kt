package com.project.onscreen.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "Employees")
data class EmployeeListDto(
    @SerializedName("id")
    @PrimaryKey val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String ,
    @SerializedName("avatar")
    val avatar: String,
)