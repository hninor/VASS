package com.hninor.vassprueba.api

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RickMortyRepositoryTest {
    private lateinit var repository: RickMortyRepository

    @Before
    fun setUp() {
        repository = RickMortyRepository()
    }

    @Test
    fun fetchCharacters() = runTest {
        val response = repository.fetchCharacters("1")
        if (response is ApiResponseStatus.Success) {
            Assert.assertNotNull(response.data)
        } else {
            assert(response is ApiResponseStatus.Error)
        }

    }
}