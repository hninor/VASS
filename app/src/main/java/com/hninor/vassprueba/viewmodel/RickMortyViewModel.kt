package com.hninor.vassprueba.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hninor.vassprueba.api.RickMortyRepository
import com.hninor.vassprueba.api.entry.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickMortyViewModel @Inject constructor(private val repository: RickMortyRepository): ViewModel() {



    var characters by mutableStateOf(listOf<Results>())
        private set

    private fun loadCharacters() {
        viewModelScope.launch {
            characters = repository.fetchCharacters()
        }
    }
}