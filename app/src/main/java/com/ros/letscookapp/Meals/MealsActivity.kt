package com.ros.letscookapp.Meals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ros.letscookapp.API.ApiClient
import com.ros.letscookapp.Details.DetailsActivity
import com.ros.letscookapp.R
import com.ros.letscookapp.SharedPreferences.Constants
import com.ros.letscookapp.SharedPreferences.SharedPref
import com.ros.letscookapp.databinding.ActivityMealsBinding

class MealsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealsBinding
    private lateinit var rv: RecyclerView
    private lateinit var viewModel: MealsViewModel
    private lateinit var sharedPref: SharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_meals)
        sharedPref = SharedPref(this)
        val service = ApiClient.getApiClient(this)?.create(MealsApiService::class.java)
        viewModel = ViewModelProvider(this).get(MealsViewModel::class.java)
        if (service != null) {
            viewModel.setService(service)
        }
        viewModel.setSharedPref(sharedPref)
        rv = binding.rvMeals
        rv.adapter = MealsAdapter(arrayListOf(), object : MealsAdapter.OnAdapterListenerMeals{
            override fun onClick(meals: MealsModel) {
                sharedPref.putString(Constants.PREF_ID_MEAL, meals.id)
                startActivity(Intent(this@MealsActivity, DetailsActivity::class.java))
            }

        })
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewModel.callApi()
        subscribeLiveData()
    }
    private fun subscribeLiveData(){
        viewModel.isMealsResponse.observe(this, Observer {
            (binding.rvMeals.adapter as MealsAdapter).addList(it)
        })
    }
}