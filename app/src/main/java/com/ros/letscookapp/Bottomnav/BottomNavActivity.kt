package com.ros.letscookapp.Bottomnav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ros.letscookapp.R
import com.ros.letscookapp.databinding.ActivityBottomNavBinding

class BottomNavActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomNavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bottom_nav)

        val bottomNavView = binding.bottomNavView
        val navController = findNavController(R.id.fragment)
        bottomNavView.setupWithNavController(navController)
    }
}