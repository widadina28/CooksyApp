package com.ros.letscookapp.SearchByName


import com.google.gson.annotations.SerializedName

data class SearchResponse(
        @SerializedName("meals")
        val meals: List<Meal>
)