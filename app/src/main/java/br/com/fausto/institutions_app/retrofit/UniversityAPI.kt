package br.com.fausto.institutions_app.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UniversityAPI {
    fun createAPI(): UniversityService? {
        return Retrofit.Builder()
            .baseUrl("http://universities.hipolabs.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UniversityService::class.java)
    }
}