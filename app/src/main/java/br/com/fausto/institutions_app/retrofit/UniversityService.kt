package br.com.fausto.institutions_app.retrofit

import br.com.fausto.institutions_app.model.UniversityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversityService {
    @GET("/search")
    suspend fun getUniversities(@Query("name") universityName: String): UniversityResponse
}