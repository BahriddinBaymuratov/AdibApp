package com.example.adabiyotapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.adabiyotapp.fragment.tablayout.MumtozAbdFragment
import com.example.adabiyotapp.fragment.tablayout.UzbekAdbFragment
import com.example.adabiyotapp.fragment.tablayout.JahonAbdFragment

class TabAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager){

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> MumtozAbdFragment()
            1 -> UzbekAdbFragment()
            else-> JahonAbdFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0-> "Mumtoz Adabiyoti"
            1-> "O'zbek Adabayoti"
            else-> "Jahon Adabiyoti"
        }
    }
}