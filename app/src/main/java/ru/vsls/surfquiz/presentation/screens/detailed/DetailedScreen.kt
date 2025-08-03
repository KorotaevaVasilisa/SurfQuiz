package ru.vsls.surfquiz.presentation.screens.detailed

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailsScreen(id: Long?, viewModel: DetailsViewModel = hiltViewModel()) {
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(id) {
        viewModel.loadDetails(id)
    }

    when {
        uiState.isLoading -> CircularProgressIndicator()
        uiState.error != null -> Text("Ошибка: ${uiState.error}")
        uiState.details != null -> {
            Text("Детали: ${uiState.details.questions.size}")
        }
    }
}