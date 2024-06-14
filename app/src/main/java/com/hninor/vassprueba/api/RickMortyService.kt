package com.hninor.vassprueba.api

import com.hninor.vassprueba.api.entry.ResponseCharacters
import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyService {


    @GET("api/character")
    suspend fun characters(@Query("page") page: String): ResponseCharacters
}
