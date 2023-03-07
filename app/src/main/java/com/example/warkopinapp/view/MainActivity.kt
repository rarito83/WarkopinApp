package com.example.warkopinapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.warkopinapp.R
import com.example.warkopinapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        tabAdapter()
    }

    private fun tabAdapter() {
        val layout = listOf(LocalFragment(), RemoteFragment())
        val tabTitle =
            listOf(resources.getString(R.string.content_tab_local), resources.getString(R.string.content_tab_remote))

        binding.viewPager.adapter = ViewPagerAdapter(layout, supportFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

    inner class ViewPagerAdapter(
        private val layout: List<Fragment>, fm: FragmentManager, lifecycle: Lifecycle
    ) : FragmentStateAdapter(fm, lifecycle) {

        override fun getItemCount(): Int = layout.size

        override fun createFragment(position: Int): Fragment = layout[position]
    }
}