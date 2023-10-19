package com.example.minggu8laprak

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

        private val page = arrayOf<Fragment>(RegisterFragment(), LoginFragment())

        override fun getItemCount(): Int {
            return page.size
        }
        override fun createFragment(position: Int): androidx.fragment.app.Fragment {
            return page[position]
        }
        fun getFragment(position: Int): Fragment {
            return page[position]
        }
}