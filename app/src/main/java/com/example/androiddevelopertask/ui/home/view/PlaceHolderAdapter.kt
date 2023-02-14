package com.example.androiddevelopertask.ui.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddevelopertask.R
import com.example.androiddevelopertask.databinding.PlaceholderRowLayoutBinding
import com.example.androiddevelopertask.databinding.ProductItemBinding

class PlaceHolderAdapter() : RecyclerView.Adapter<PlaceHolderAdapter.PlaceHolderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PlaceholderRowLayoutBinding.inflate(layoutInflater, parent, false)
        return PlaceHolderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceHolderAdapter.PlaceHolderViewHolder, position: Int) {

    }

    override fun getItemCount() = 6

    class PlaceHolderViewHolder(var view: PlaceholderRowLayoutBinding) : RecyclerView.ViewHolder(view.root)
}