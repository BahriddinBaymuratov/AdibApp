package com.example.adabiyotapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adabiyotapp.databinding.ItemLayoutBinding
import com.example.adabiyotapp.model.Adib
import com.squareup.picasso.Picasso

class AdibAdapter : ListAdapter<Adib, AdibAdapter.AdibViewHolder>(DiffCallback()) {
    lateinit var onClick: (Adib) -> Unit

    private class DiffCallback : DiffUtil.ItemCallback<Adib>() {
        override fun areItemsTheSame(oldItem: Adib, newItem: Adib): Boolean {
            return oldItem.fullName == newItem.fullName
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
                Glide.with(imageView)
                    .load(adib.image)
                    .into(imageView)

                textName.text = adib.fullName
                textDead.text = adib.dateDead
            }
            itemView.setOnClickListener {
                onClick(adib)
            }
        }
    }
}