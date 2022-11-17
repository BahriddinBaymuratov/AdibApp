package com.example.adabiyotapp.fragment.bottomnav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.adabiyotapp.R
import com.example.adabiyotapp.adapter.TabAdapter
import com.example.adabiyotapp.databinding.FragmentAllAdbBinding
import com.google.android.material.tabs.TabLayout

class AllAdbFragment : Fragment() {
    private var _binding: FragmentAllAdbBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllAdbBinding.inflate(inflater ,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val tabAdapter = TabAdapter(this)

        binding.tabLayout22.addTab(binding.tabLayout22.newTab().setText("Mumtoz Adabiyoti"))
        binding.tabLayout22.addTab(binding.tabLayout22.newTab().setText("O'zbek Adabiyoti"))
        binding.tabLayout22.addTab(binding.tabLayout22.newTab().setText("Jahon Adabiyoti"))
        binding.viewPager22.adapter = tabAdapter
        binding.tabLayout22.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager22.currentItem = tab?.position!!
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        binding.viewPager22.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout22.selectTab(binding.tabLayout22.getTabAt(position))
            }
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}