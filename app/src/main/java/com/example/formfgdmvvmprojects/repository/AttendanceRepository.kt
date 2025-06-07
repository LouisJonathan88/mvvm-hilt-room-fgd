package com.example.formfgdmvvmprojects.repository

import com.example.formfgdmvvmprojects.data.AttendanceDao
import com.example.formfgdmvvmprojects.model.AttendanceModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class AttendanceRepository @Inject constructor(private val attendanceDao: AttendanceDao) {
    suspend fun insert(attendance: AttendanceModel) = attendanceDao.insert(attendance)
    fun getAll():Flow<List<AttendanceModel>> = attendanceDao.getAll()
}