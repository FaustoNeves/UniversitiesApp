package br.com.fausto.institutions_app.retrofit

import br.com.fausto.institutions_app.model.UniversityParsed
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversityService {
    @GET("/search")
    suspend fun getUniversities(@Query("name") universityName: String): Response<UniversityParsed>
}