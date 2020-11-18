package com.ros.letscookapp.Details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ros.letscookapp.API.ApiClient
import com.ros.letscookapp.R
import com.ros.letscookapp.SharedPreferences.Constants
import com.ros.letscookapp.SharedPreferences.SharedPref
import com.ros.letscookapp.Youtube.YoutubeActivity
import com.ros.letscookapp.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: DetailsViewModel
    private lateinit var sharedPref: SharedPref
    private lateinit var rv: TagsAdapter
    private lateinit var rvI: IngredientsAdapter
    private lateinit var rvM: MeasureAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        sharedPref = SharedPref(this)
        val service = ApiClient.getApiClient(this)?.create(DetailsApiService::class.java)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        if (service != null) {
            viewModel.setService(service)
        }
        viewModel.setSharedPref(sharedPref)
        viewModel.callApi()
        binding.buttonYoutube.setOnClickListener {
            startActivity(Intent(this, YoutubeActivity::class.java))
        }
        subscribeLiveData()
    }

    private fun subscribeLiveData() {
        viewModel.isResponseDetail.observe(this, Observer {
            binding.nameDetail.text = it.meals[0].strMeal
            binding.categoryDetail.text = it.meals[0].strCategory
            Picasso.get().load(it.meals[0].strMealThumb).into(binding.ivDetails)
            binding.tvInstructionDetail.text = it.meals[0].strInstructions

            var ingredients = arrayListOf<String>(it.meals[0].strIngredient1, it.meals[0].strIngredient2,
                    it.meals[0].strIngredient3, it.meals[0].strIngredient4, it.meals[0].strIngredient5, it.meals[0].strIngredient6,
                    it.meals[0].strIngredient7, it.meals[0].strIngredient8, it.meals[0].strIngredient9, it.meals[0].strIngredient10,
                    it.meals[0].strIngredient11, it.meals[0].strIngredient12, it.meals[0].strIngredient13, it.meals[0].strIngredient14,
                    it.meals[0].strIngredient15, it.meals[0].strIngredient16, it.meals[0].strIngredient17, it.meals[0].strIngredient18,
                    it.meals[0].strIngredient20)
            var numbI : Int? = null
            if(ingredients.contains("")) {
                numbI = ingredients.indexOf("")-1
            } else if (ingredients.contains(" ")) {
                numbI = ingredients.indexOf(" ")-1
            } else if (ingredients.contains("null")) {
                numbI = ingredients.indexOf("null")-1
            }
            var ingredientsI = ingredients.slice(0..numbI!!)

            var dataIngredients = ingredientsI.map {
                IngredientsModel(it)
            }

            rvI = IngredientsAdapter(dataIngredients)
            binding.rvIngredients.adapter = rvI
            binding.rvIngredients.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


            var measure = arrayListOf<String>(it.meals[0].strMeasure1, it.meals[0].strMeasure2, it.meals[0].strMeasure3,
                    it.meals[0].strMeasure4, it.meals[0].strMeasure5, it.meals[0].strMeasure6, it.meals[0].strMeasure7, it.meals[0].strMeasure8,
                    it.meals[0].strMeasure9, it.meals[0].strMeasure10, it.meals[0].strMeasure11, it.meals[0].strMeasure12, it.meals[0].strMeasure13,
                    it.meals[0].strMeasure14, it.meals[0].strMeasure15, it.meals[0].strMeasure17,
                    it.meals[0].strMeasure18, it.meals[0].strMeasure19, it.meals[0].strMeasure20)
            var numbM : Int? = null
             if(measure.contains("")) {
                 numbM = measure.indexOf("")-1
             } else if (measure.contains(" ")) {
                 numbM = measure.indexOf(" ")-1
             } else if (measure.contains("null")) {
                 numbM = measure.indexOf("null")-1
             }


            var measureM = measure.slice(0..numbM!!)


            var dataMeasure = measureM.map {
                MeasureModel(it)
            }
            rvM = MeasureAdapter(dataMeasure)
            binding.rvMeasure.adapter = rvM
            binding.rvMeasure.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


            var data = if (it.meals[0].strTags != null) it.meals[0].strTags.split(",").map {
                TagsModel(it)
            } else ArrayList<TagsModel>()

            rv = TagsAdapter(data)
            binding.rvTags.adapter = rv
            binding.rvTags.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            sharedPref.putString(Constants.PREF_YOUTUBE, it.meals[0].strYoutube)
        })

    }
}