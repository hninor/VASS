package com.hninor.vassprueba.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hninor.vassprueba.api.RickMortyRepository
import com.hninor.vassprueba.api.entry.Info
import com.hninor.vassprueba.api.entry.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickMortyViewModel @Inject constructor(private val repository: RickMortyRepository) :
    ViewModel() {


    var characters by mutableStateOf(listOf<Results>())
        private set

    var page: String? by mutableStateOf("1")

    var info: Info? = null

    lateinit var characterSelected: Results

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            val response = repository.fetchCharacters(page!!)
            characters = characters + response.results
            info = response.info
        }
    }

    fun loadNextPage() {
        if (info?.next != null) {
            page = info?.next?.split("=")?.get(1)
            loadCharacters()
        }
    }


}