package com.example.openingtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openingtask.model.ListedDataItem
import com.example.openingtask.repository.Repository
import com.example.openingtask.utility.ApiResponce
import kotlinx.coroutines.launch

class ViewModelData(private val repository: Repository) : ViewModel() {
    val liveData: LiveData<ApiResponce<ListedDataItem>> = repository.liveData
    fun getApiData(){
        viewModelScope.launch {
            repository.getApiData()
        }
    }
}