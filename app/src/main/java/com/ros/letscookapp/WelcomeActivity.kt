package com.ros.letscookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ros.letscookapp.Bottomnav.BottomNavActivity
import com.ros.letscookapp.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        binding.btnStart.setOnClickListener {
            startActivity(Intent(this, BottomNavActivity::class.java))
        }
    }
}