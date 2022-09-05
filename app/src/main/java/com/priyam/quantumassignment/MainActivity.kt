package com.priyam.quantumassignment



import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager2: ViewPager2
    lateinit var adapter: MyFragmentAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager2)

        tabLayout.addTab(tabLayout.newTab().setText("Sign In"))
        tabLayout.addTab(tabLayout.newTab().setText("Sign Up"))

        val fragmentManager: FragmentManager = supportFragmentManager
        adapter = MyFragmentAdapter(fragmentManager, lifecycle)
        viewPager2.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager2.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

    }


}
