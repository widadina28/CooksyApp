package com.ros.letscookapp.SearchByName

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {
    @GET("search.php")
    fun getSearch(@Query("s") search: String?): Call<SearchResponse>
}