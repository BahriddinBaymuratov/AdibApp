package com.example.adabiyotapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.adabiyotapp.databinding.ItemLayoutBinding
import com.example.adabiyotapp.model.Adib
import com.squareup.picasso.Picasso

class AdibAdapter : ListAdapter<Adib, AdibAdapter.AdibViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<Adib>() {
        override fun areItemsTheSame(oldItem: Adib, newItem: Adib): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Adib, newItem: Adib): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdibViewHolder {
        return AdibViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AdibViewHolder, position: Int) {
        holder.find(getItem(position))
    }

    inner class AdibViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun find(adib: Adib) {
            binding.apply {
                Picasso.get()
                    .load(adib.image)
                    .into(imageView)

                textName.text = adib.name
                textDead.text = adib.dateDead
                textUserName.text = adib.name
            }
            notifyDataSetChanged()
        }
    }
}