package com.ros.letscookapp.Meals

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ros.letscookapp.SharedPreferences.Constants
import com.ros.letscookapp.SharedPreferences.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsViewModel : ViewModel() {
    val isMealsResponse = MutableLiveData<List<MealsModel>>()
    private lateinit var service: MealsApiService
    private lateinit var sharedPref: SharedPref

    fun setSharedPref(sharedPref: SharedPref) {
        this.sharedPref = sharedPref
    }

    fun setService(service: MealsApiService) {
        this.service = service
    }

    fun callApi() {
        var c = sharedPref.getString(Constants.PREF_CATEGORY)
        service.getMealsByCategory(c).enqueue(object : Callback<MealsByCategoryResponse> {
            override fun onFailure(call: Call<MealsByCategoryResponse>, t: Throwable) {

            }

            override fun onResponse(
                    call: Call<MealsByCategoryResponse>,
                    response: Response<MealsByCategoryResponse>
            ) {
                val list = response.body()?.meals?.map {
                    MealsModel(it.idMeal.orEmpty(), it.strMeal.orEmpty(), it.strMealThumb.orEmpty())
                } ?: listOf()
                isMealsResponse.value = list
            }

        })
    }
}