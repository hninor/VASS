package com.hninor.vassprueba.viewmodel

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hninor.vassprueba.api.ApiResponseStatus
import com.hninor.vassprueba.api.RickMortyRepository
import com.hninor.vassprueba.api.entry.Info
import com.hninor.vassprueba.api.entry.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickMortyViewModel @Inject constructor(private val repository: RickMortyRepository) :
    ViewModel() {

    val snackbarHostState = SnackbarHostState()
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
            if (response is ApiResponseStatus.Success) {
                characters = characters + response.data.results
                info = response.data.info
            } else if (response is ApiResponseStatus.Error) {
                snackbarHostState.showSnackbar(
                    message = response.message,
                    duration = SnackbarDuration.Short
                )
            }

        }
    }

    fun loadNextPage() {
        if (info?.next != null) {
            page = info?.next?.split("=")?.get(1)
            loadCharacters()
        }
    }


}