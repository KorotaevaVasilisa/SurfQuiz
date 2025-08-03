package ru.vsls.surfquiz.presentation.screens.history

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import okhttp3.internal.platform.PlatformRegistry.applicationContext
import ru.vsls.surfquiz.R
import ru.vsls.surfquiz.presentation.items.InfoCard
import ru.vsls.surfquiz.ui.theme.SurfQuizTheme

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel(),
    onBackToStart: () -> Unit = {},
    onNavigateDetailedScreen: (id: Long) -> Unit,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadHistory() }
    LaunchedEffect(Unit) {
        viewModel.toastMessage.collect { message ->
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }
    when {
        state.isLoading -> CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
        state.error != null -> Text(
            "Ошибка: ${state.error}",
            color = MaterialTheme.colorScheme.error
        )

        state.history.isEmpty() -> InfoScreen(onBackToStart)

        else -> LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.history) { entry ->
                val isSelected = state.selectedItemId == entry.id
                val isDimmed = state.selectedItemId != null && !isSelected
                HistoryItem(
                    model = entry,
                    isDimmed = isDimmed,
                    isSelected = isSelected,
                    onLongClick = viewModel::selectItem,
                    onDelete = { viewModel.deleteEntry(entry.id) },
                    onNextScreen = { onNavigateDetailedScreen(entry.id) }
                )
            }
        }
    }
}


@Composable
fun InfoScreen(onBackToStart: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        InfoCard(onBackToStart)
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 40.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    SurfQuizTheme {
        HistoryItem(
            model = QuizHistoryUiModel(
                id = 222,
                title = "Quiz 1",
                formattedDate = "14.06.2024 19:15",
                correctAnswers = 4,
                totalQuestions = 5,
                difficulty = "medium"
            ),
            isDimmed = false,
            isSelected = false,
            onLongClick = {},
            onDelete = {},
            onNextScreen = {}
        )
    }
}