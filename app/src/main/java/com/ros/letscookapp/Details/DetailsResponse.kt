package com.ros.letscookapp.Details


import com.google.gson.annotations.SerializedName

data class DetailsResponse(
        @SerializedName("meals")
        val meals: List<Meal>
)