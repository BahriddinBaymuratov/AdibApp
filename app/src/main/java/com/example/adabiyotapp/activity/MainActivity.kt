package com.example.adabiyotapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
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
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.allAdbFragment, R.id.favoriteFragment, R.id.settingFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.bottom_menu)
        val menu = popupMenu.menu
        binding.bottomBar.setupWithNavController(menu, navController)

    }

}