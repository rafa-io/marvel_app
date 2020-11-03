package com.rafaelsoares.marvel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayout
import com.rafaelsoares.marvel.adapter.ViewPagerAdapter
import com.rafaelsoares.marvel.ui.home.HomeFragment
import com.rafaelsoares.marvel.ui.qrcode.QRCodeFragment
import com.rafaelsoares.marvel.ui.superheroes.SuperHeroesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupTabs()
    }

    private fun setupTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "Home")
        adapter.addFragment(SuperHeroesFragment(), "Super Heróis")
        adapter.addFragment(QRCodeFragment(), "QRCode")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }
}