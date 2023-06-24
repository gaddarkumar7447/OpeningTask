package com.example.openingtask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.openingtask.model.ListedDataItem
import com.example.openingtask.network.ApiServices
import com.example.openingtask.utility.ApiResponce

class Repository(private val apiServices: ApiServices) {
    private val mutableLiveData : MutableLiveData<ApiResponce<ListedDataItem>> = MutableLiveData()
    val liveData : LiveData<ApiResponce<ListedDataItem>> = mutableLiveData

    suspend fun getApiData(){
        try {
            mutableLiveData.postValue(ApiResponce.Loading())
            val responce = apiServices.getApiResponce().body()
            if (responce != null){
                mutableLiveData.postValue(ApiResponce.Success(responce))
            }else{
                mutableLiveData.postValue(ApiResponce.Error("No data"))
            }
        }catch (e : Exception){
            mutableLiveData.postValue(ApiResponce.Error("Something went to wrong"))
        }
    }
}