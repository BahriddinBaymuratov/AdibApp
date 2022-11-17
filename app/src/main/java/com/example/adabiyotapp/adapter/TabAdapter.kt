package com.example.adabiyotapp.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.adabiyotapp.fragment.tablayout.MumtozAbdFragment
import com.example.adabiyotapp.fragment.tablayout.UzbekAdbFragment
import com.example.adabiyotapp.fragment.tablayout.JahonAbdFragment

class TabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MumtozAbdFragment()
            1 -> UzbekAdbFragment()
            2 -> JahonAbdFragment()
            else -> Fragment()
        }
    }


}