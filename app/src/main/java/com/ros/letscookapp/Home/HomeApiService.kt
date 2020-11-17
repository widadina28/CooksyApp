package com.ros.letscookapp.Home

import retrofit2.Call
import retrofit2.http.GET

interface HomeApiService {
    @GET("categories.php")
    fun getAllMeals(): Call<CategoryResponse>
}