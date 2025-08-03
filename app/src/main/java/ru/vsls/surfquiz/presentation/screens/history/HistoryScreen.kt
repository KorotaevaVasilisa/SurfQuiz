package ru.vsls.surfquiz.presentation.screens.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.vsls.surfquiz.domain.model.QuizHistoryEntry
import ru.vsls.surfquiz.ui.theme.SurfQuizTheme

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel(),
    onItemClicked: (QuizHistoryEntry) -> Unit = {},
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadHistory() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()
            state.error != null -> Text(
                "Ошибка: ${state.error}",
                color = MaterialTheme.colorScheme.error
            )

            state.history.isEmpty() -> Text(
                "История пуста",
                color = MaterialTheme.colorScheme.secondary
            )

            else -> LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.history) { entry ->
                    HistoryItem(entry, onClick = { onItemClicked(entry) })
                }
            }
        }
    }
}

@Composable
fun HistoryItem(entry: QuizHistoryEntry, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Дата: ${
                    java.text.SimpleDateFormat("dd.MM.yyyy HH:mm")
                        .format(java.util.Date(entry.dateTime))
                }"
            )
            Text("Верных: ${entry.correctAnswers} из ${entry.totalQuestions}")
            Text("Сложность: ${entry.difficulty}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    SurfQuizTheme {
        HistoryScreen()
    }
}