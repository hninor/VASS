package com.hninor.vassprueba.api

import com.hninor.vassprueba.api.entry.ResponseCharacters
import java.net.UnknownHostException
import javax.inject.Inject

class RickMortyRepository @Inject constructor() {


    suspend fun fetchCharacters(page: String): ApiResponseStatus<ResponseCharacters> {
        try {
            val response = reqApi.characters(page)

            return if (response.isSuccessful) {
                if (response.body() == null) {
                    ApiResponseStatus.Error("Error no esperado: ${response.errorBody()}")
                } else {
                    ApiResponseStatus.Success(response.body()!!)
                }
            } else {
                ApiResponseStatus.Error("Http code: $response.code()")
            }
        } catch (e: UnknownHostException) {
            return ApiResponseStatus.Error("Revise su conexión a internet")
        }

    }
}
