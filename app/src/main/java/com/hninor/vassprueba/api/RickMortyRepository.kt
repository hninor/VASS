package com.hninor.vassprueba.api

import com.hninor.vassprueba.api.entry.Results
import javax.inject.Inject

class RickMortyRepository @Inject constructor() {


    suspend fun fetchCharacters(): List<Results> {
        return reqApi.characters("1").results
    }
}
