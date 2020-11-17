package com.ros.letscookapp.Meals

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MealsApiService {
    @GET("filter.php")
    fun getMealsByCategory(@Query("c") category: String?) : Call<MealsByCategoryResponse>
}