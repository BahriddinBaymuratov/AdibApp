package com.example.adabiyotapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.ViewPager
import com.example.adabiyotapp.R
import com.example.adabiyotapp.adapter.TabAdapter
import com.example.adabiyotapp.databinding.ActivityMainBinding
import com.example.adabiyotapp.fragment.bottomnav.AllAdbFragment
import com.example.adabiyotapp.fragment.bottomnav.SettingFragment
import com.example.adabiyotapp.fragment.tablayout.UzbekAdbFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var tabAdapter: TabAdapter
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.add.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        navController = findNavController(R.id.nav_host_fragment_home)
        binding.bottomNav.setupWithNavController(navController)

        tabAdapter = TabAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.viewPager.adapter = tabAdapter

    }

}