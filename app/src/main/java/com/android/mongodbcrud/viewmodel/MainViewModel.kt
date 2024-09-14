package com.android.mongodbcrud.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.mongodbcrud.model.BasicInfo
import com.android.mongodbcrud.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository : MainRepository) : ViewModel() {

    private var _data = MutableLiveData<List<BasicInfo>>(emptyList())
    val data : LiveData<List<BasicInfo>> get() = _data


    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            _data.postValue(repository.getData())
        }
    }

    fun insertBasicInfo(info : BasicInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertBasicInfo(info)
            getData()
        }
    }

    fun updateBasicInfo(info : BasicInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBasicInfo(info)
        }
    }

    fun deleteBasicInfo(info : BasicInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBasicInfo(info)
        }
    }

    fun deleteAllBasicInfo() {
        viewModelScope.launch {
            repository.deleteAllBasicInfo()
            getData()
        }
    }
}