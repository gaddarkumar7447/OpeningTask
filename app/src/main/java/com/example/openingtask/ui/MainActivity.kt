package com.example.openingtask.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.openingtask.R
import com.example.openingtask.adapter.AdapterData
import com.example.openingtask.databinding.ActivityMainBinding
import com.example.openingtask.databinding.FragmentTopLinkBinding
import com.example.openingtask.fragments.PageAdapter
import com.example.openingtask.model.ListedDataItem
import com.example.openingtask.network.ApiInstance
import com.example.openingtask.network.ApiServices
import com.example.openingtask.repository.Repository
import com.example.openingtask.utility.ApiResponce
import com.example.openingtask.viewmodel.ViewModelData
import com.example.openingtask.viewmodel.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ViewModelData
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpFragments()

        initializeViewModel()
        val res = ApiInstance.getInstance(this@MainActivity).create(ApiServices::class.java)
        res.getApiResponceForCheck().enqueue(object : Callback<ListedDataItem?> {
            override fun onResponse(
                call: Call<ListedDataItem?>,
                response: Response<ListedDataItem?>
            ) {
                Log.d("REDD", response.body()?.data?.top_links.toString())
            }

            override fun onFailure(call: Call<ListedDataItem?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        fetchData()

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun fetchData() {
        viewModel.liveData.observe(this, Observer {
            when (it) {
                is ApiResponce.Loading -> {

                }

                is ApiResponce.Success -> {
                    val data = it.data?.data?.top_links
                    Log.d("Data11", data.toString())
                }

                is ApiResponce.Error -> {

                }
            }
        })
    }

    private fun initializeViewModel() {
        val instance = ApiInstance.getInstance(this).create(ApiServices::class.java)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(Repository(instance))
        )[ViewModelData::class.java]
    }

    private fun setUpFragments() {
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        val adapter = PageAdapter(supportFragmentManager, lifecycle)

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Top Links"
                1 -> "Recent Links"
                else -> "Top Links"
            }
        }.attach()
    }
}