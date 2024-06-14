package com.hninor.vassprueba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hninor.vassprueba.screens.LaunchList
import com.hninor.vassprueba.ui.theme.VassPruebaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VassPruebaTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                // A surface container using the 'background' color from the theme
                Scaffold(topBar = { TopAppBar({ Text(stringResource(R.string.app_name)) }) },
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                ) { paddingValues ->
                    Box(Modifier.padding(paddingValues)) {
                        MainNavHost()
                    }
                }
            }
        }
    }
}



@Composable
private fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationDestinations.LAUNCH_LIST) {
        composable(route = NavigationDestinations.LAUNCH_LIST) {
            LaunchList(
                onCharacterClick = { launchId ->
                    navController.navigate("${NavigationDestinations.LAUNCH_DETAILS}/$launchId")
                }
            )
        }

        composable(route = "${NavigationDestinations.LAUNCH_DETAILS}/{${NavigationArguments.LAUNCH_ID}}") { navBackStackEntry ->
/*            LaunchDetails(launchId = navBackStackEntry.arguments!!.getString(NavigationArguments.LAUNCH_ID)!!,
                navigateToLogin = {
                    navController.navigate(NavigationDestinations.LOGIN)
                }
            )*/
        }

    }
}