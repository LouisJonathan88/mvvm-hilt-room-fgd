package com.example.formfgdmvvmprojects.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attendance")
data class AttendanceModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val gender: String = "",
    val skillset: String = "",
    val kategori: String = ""
)
