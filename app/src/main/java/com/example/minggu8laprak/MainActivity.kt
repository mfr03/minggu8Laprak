package com.example.minggu8laprak

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.CheckedTextView
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.minggu8laprak.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.util.regex.Pattern

class MainActivity : AppCompatActivity(), FragmentRegisterListener{
    private lateinit var binding : ActivityMainBinding
    private lateinit var tabAdapter : TabAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var fragmentLogin: LoginFragment
    private lateinit var fragmentRegister: RegisterFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        tabAdapter = TabAdapter(this@MainActivity)

        setContentView(binding.root)
        with(binding) {
            viewPager.adapter = tabAdapter
            viewPager2 = viewPager
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when(position) {
                    0 -> "Register"
                    1 -> "Login"
                    else -> "Undefined"
                }
            }.attach()
        }

    }

    override fun onInformationReceived(username: String, password: String) {
        val bundle = Bundle()
        bundle.putString("username", username)
        bundle.putString("password", password)
        val fragment = tabAdapter.getFragment(1)
        fragment.arguments = bundle
        viewPager2.setCurrentItem(1, true)

    }


}