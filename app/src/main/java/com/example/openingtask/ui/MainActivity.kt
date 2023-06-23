package com.example.openingtask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.openingtask.databinding.ActivityMainBinding
import com.example.openingtask.model.ListedDataItem
import com.example.openingtask.network.ApiInstance
import com.example.openingtask.network.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val instance = ApiInstance.getInstance().create(ApiServices::class.java)
        instance.getApiResponce().enqueue(object : Callback<ListedDataItem?> {
            override fun onResponse(
                call: Call<ListedDataItem?>,
                response: Response<ListedDataItem?>
            ) {
                Log.d("Data", response.body().toString())
                binding.textView.text = response.body().toString()
            }

            override fun onFailure(call: Call<ListedDataItem?>, t: Throwable) {
                Log.d("Data", t.toString())
            }
        })

    }
}