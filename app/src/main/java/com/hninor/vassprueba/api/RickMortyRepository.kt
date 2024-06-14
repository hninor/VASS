package com.hninor.vassprueba.api

import com.hninor.vassprueba.api.entry.ResponseCharacters
import com.hninor.vassprueba.api.entry.Results
import javax.inject.Inject

class RickMortyRepository @Inject constructor() {


    suspend fun fetchCharacters(page: String): ResponseCharacters {
        return reqApi.characters(page)
    }
}
