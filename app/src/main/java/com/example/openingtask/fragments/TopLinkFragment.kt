package com.example.openingtask.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openingtask.adapter.AdapterData
import com.example.openingtask.databinding.FragmentTopLinkBinding
import com.example.openingtask.model.TopLink
import com.example.openingtask.network.ApiInstance
import com.example.openingtask.network.ApiServices
import com.example.openingtask.repository.Repository
import com.example.openingtask.utility.ApiResponce
import com.example.openingtask.viewmodel.ViewModelData
import com.example.openingtask.viewmodel.ViewModelFactory

class TopLinkFragment : Fragment() {
    private lateinit var viewModel: ViewModelData
    private lateinit var binding : FragmentTopLinkBinding
    private lateinit var adapterData: AdapterData
    private lateinit var listTopLink: List<TopLink>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTopLinkBinding.inflate(layoutInflater, container,false)

        //initializeViewModel()

        //fetchData()

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchData() {
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is ApiResponce.Loading ->{

                }
                is ApiResponce.Success ->{
                    val data = it.data?.data?.top_links
                    adapterData = AdapterData(data!!)
                    binding.rvTopLink.adapter = adapterData
                    binding.rvTopLink.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvTopLink.setHasFixedSize(true)
                    Log.d("Data", data.toString())
                    adapterData.notifyDataSetChanged()
                }
                is ApiResponce.Error ->{

                }
            }
        })
    }

    private fun initializeViewModel() {
        val instance = ApiInstance.getInstance(requireContext()).create(ApiServices::class.java)
        viewModel = ViewModelProvider(this, ViewModelFactory(Repository(instance)))[ViewModelData::class.java]
    }

}