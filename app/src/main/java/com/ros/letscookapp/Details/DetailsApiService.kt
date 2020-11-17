package com.ros.letscookapp.Details

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailsApiService {
    @GET("lookup.php")
    fun getDetails(@Query("i") id: String?) : Call<DetailsResponse>
}