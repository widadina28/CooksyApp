package com.ros.letscookapp.API

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private const val URL = "https://www.themealdb.com/api/json/v1/1/"
        private var retrofit: Retrofit? = null

        private fun provideHttpLoggingInceptor() = run {
            HttpLoggingInterceptor().apply {
                apply { level = HttpLoggingInterceptor.Level.BODY }
            }
        }

        fun getApiClient(mcontext: Context): Retrofit? {
            if (retrofit == null) {
                val okHttpClient = OkHttpClient.Builder()
                        .addInterceptor(provideHttpLoggingInceptor())
                        .build()
                retrofit = Retrofit.Builder()
                        .baseUrl(URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }
    }
}