package com.ros.letscookapp.Youtube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ros.letscookapp.R
import com.ros.letscookapp.SharedPreferences.Constants
import com.ros.letscookapp.SharedPreferences.SharedPref
import com.ros.letscookapp.databinding.ActivityYoutubeBinding

class YoutubeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYoutubeBinding
    private lateinit var sharedPref: SharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_youtube)
        sharedPref = SharedPref(this)
        var url = sharedPref.getString(Constants.PREF_YOUTUBE)
        binding.webview.loadUrl("$url")

    }
}