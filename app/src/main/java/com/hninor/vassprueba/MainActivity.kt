package com.hninor.vassprueba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hninor.vassprueba.screens.CharacterDetails
import com.hninor.vassprueba.screens.CharacterList
import com.hninor.vassprueba.ui.theme.VassPruebaTheme
import com.hninor.vassprueba.viewmodel.RickMortyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val viewModel: RickMortyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VassPruebaTheme {

                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = { TopAppBar({ Text(stringResource(R.string.app_name)) }) },
                    snackbarHost = { SnackbarHost(viewModel.snackbarHostState) },
                ) { paddingValues ->
                    Box(Modifier.padding(paddingValues)) {
                        MainNavHost(viewModel)
                    }
                }
            }
        }
    }
}


@Composable
private fun MainNavHost(viewModel: RickMortyViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationDestinations.CHARACTER_LIST) {
        composable(route = NavigationDestinations.CHARACTER_LIST) {
            CharacterList(
                characters = viewModel.characters,
                onCharacterClick = { character ->
                    viewModel.characterSelected = character
                    navController.navigate(NavigationDestinations.CHARACTER_DETAILS)
                },
                onLoadNextPage = {
                    viewModel.loadNextPage()
                }

            )
        }

        composable(route = NavigationDestinations.CHARACTER_DETAILS) { navBackStackEntry ->
            CharacterDetails(character = viewModel.characterSelected)
        }

    }
}