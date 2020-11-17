package com.ros.letscookapp.SearchByName

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ros.letscookapp.Meals.MealsModel
import com.ros.letscookapp.SharedPreferences.Constants
import com.ros.letscookapp.SharedPreferences.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {
    val isResponseSearch = MutableLiveData<List<MealsModel>>()
    private lateinit var service: SearchApiService
    private lateinit var sharedPref: SharedPref

    fun setService(service: SearchApiService){
        this.service = service
    }

    fun setSharedPref(sharedPref: SharedPref){
        this.sharedPref = sharedPref
    }

    fun callApi(){
        var s = sharedPref.getString(Constants.PREF_SEARCH)
        service.getSearch(s).enqueue(object : Callback<SearchResponse>{
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                val list = response.body()?.meals?.map {
                    MealsModel(it.idMeal.orEmpty(), it.strMeal.orEmpty(), it.strMealThumb.orEmpty())
                } ?: listOf()
                isResponseSearch.value = list
            }

        })

    }
}