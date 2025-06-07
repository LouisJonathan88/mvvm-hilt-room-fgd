package com.example.formfgdmvvmprojects.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.formfgdmvvmprojects.model.AttendanceModel
import com.example.formfgdmvvmprojects.repository.AttendanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AttendanceViewModel @Inject constructor(
    private val repository: AttendanceRepository
) : ViewModel() {
    private val _attendanceData = MutableLiveData<AttendanceModel>()
    val attendanceData: LiveData<AttendanceModel> get() = _attendanceData

    val attendanceList = repository.getAll().asLiveData()

    //fungi menyimpan data peserta
    fun setAttendanceData(model: AttendanceModel) {
//        _attendanceData.value = model
        viewModelScope.launch {
            repository.insert(model)
        }
    }
}