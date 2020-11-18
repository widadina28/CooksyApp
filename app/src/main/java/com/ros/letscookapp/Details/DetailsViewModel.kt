package com.ros.letscookapp.Details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ros.letscookapp.SharedPreferences.Constants
import com.ros.letscookapp.SharedPreferences.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel : ViewModel() {
    val isResponseDetail = MutableLiveData<DetailsResponse>()
    private lateinit var service: DetailsApiService
    private lateinit var sharedPref: SharedPref

    fun setSharedPref(sharedPref: SharedPref) {
        this.sharedPref = sharedPref
    }

    fun setService(service: DetailsApiService) {
        this.service = service
    }

    fun callApi() {
        var id = sharedPref.getString(Constants.PREF_ID_MEAL)
        service.getDetails(id).enqueue(object : Callback<DetailsResponse> {
            override fun onFailure(call: Call<DetailsResponse>, t: Throwable) {

            }

            override fun onResponse(
                    call: Call<DetailsResponse>,
                    response: Response<DetailsResponse>
            ) {
                isResponseDetail.value = response.body()
            }
        })
    }
}