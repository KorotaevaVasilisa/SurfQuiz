package ru.vsls.surfquiz

import ru.vsls.surfquiz.presentation.navigation.Screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.vsls.surfquiz.ui.theme.SurfQuizTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import dagger.hilt.android.AndroidEntryPoint
import ru.vsls.surfquiz.presentation.HistoryScreen
import ru.vsls.surfquiz.presentation.QuizScreen

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SurfQuizTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute = navBackStackEntry?.destination?.route
                        when (currentRoute) {
                            Screen.Quiz.route, null -> {
                                TopAppBar(
                                    title = { Text(stringResource(R.string.dailyquiz)) },
                                    actions = {
                                        IconButton(onClick = { navController.navigate(Screen.History.route) }) {
                                            Icon(
                                                imageVector = Icons.Filled.DateRange,
                                                contentDescription = stringResource(R.string.history)
                                            )
                                        }
                                    }
                                )
                            }

                            Screen.History.route ->
                                TopAppBar(
                                    title = { Text(stringResource(R.string.history)) },
                                    navigationIcon = {
                                        IconButton(onClick = { navController.popBackStack() }) {
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                contentDescription = stringResource(R.string.back)
                                            )
                                        }
                                    }
                                )

                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    QuizNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun QuizNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Quiz.route,
        modifier = modifier
    ) {
        composable(Screen.Quiz.route) {
            QuizScreen()
        }
        composable(Screen.History.route) {
            HistoryScreen()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    SurfQuizTheme {
        QuizScreen()
    }
}