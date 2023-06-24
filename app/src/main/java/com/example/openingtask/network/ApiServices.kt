package com.example.openingtask.network

import com.example.openingtask.model.ListedDataItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("dashboardNew")
    suspend fun getApiResponce() : Response<ListedDataItem>

    @GET("dashboardNew")
    fun getApiResponceForCheck() : Call<ListedDataItem>
}