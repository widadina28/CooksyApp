package com.ros.letscookapp.Home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ros.letscookapp.SharedPreferences.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    val isResponseAllMeals = MutableLiveData<List<CategoryModel>>()
    private lateinit var service: HomeApiService

    fun setService(service: HomeApiService) {
        this.service = service
    }

    fun callApi() {
        service.getAllMeals().enqueue(object : Callback<CategoryResponse> {
            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                val list = response.body()?.categories?.map {
                    CategoryModel(it.idCategory.orEmpty(), it.strCategory.orEmpty(), it.strCategoryThumb.orEmpty())
                } ?: listOf()
                isResponseAllMeals.value = list

            }

        })
    }
}