package com.ros.letscookapp.Meals


import com.google.gson.annotations.SerializedName

data class MealsByCategoryResponse(
        @SerializedName("meals")
        val meals: List<Meal>
)