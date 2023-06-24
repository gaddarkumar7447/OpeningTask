package com.example.openingtask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.openingtask.databinding.ItemLinkViewBinding
import com.example.openingtask.model.TopLink

class AdapterData(private val topListData : List<TopLink>) : RecyclerView.Adapter<AdapterData.ViewHolderData>(){
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderData {
        context = parent.context
        return ViewHolderData(ItemLinkViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return topListData.size
    }

    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        val currentItem = topListData[position]
        holder.itemLinkViewBinding.apply {
            Glide.with(context).load(currentItem.original_image).into(icon)
            tvTitle.text = currentItem.title
            tvClicks.text = currentItem.total_clicks.toString()
            tvSubTitle.text = currentItem.times_ago
            tvLink.text = currentItem.web_link
        }
    }

    class ViewHolderData(val itemLinkViewBinding: ItemLinkViewBinding) : ViewHolder(itemLinkViewBinding.root)
}