package ru.vsls.surfquiz.presentation.screens.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.vsls.surfquiz.R
import ru.vsls.surfquiz.domain.model.QuizHistoryEntry
import ru.vsls.surfquiz.presentation.items.InfoCard
import ru.vsls.surfquiz.presentation.items.ScoreStars
import ru.vsls.surfquiz.ui.theme.SurfQuizTheme
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel(),
    onBackToStart: () -> Unit = {},
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadHistory() }

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
            itemsIndexed(state.history) { index, entry ->
                HistoryItem(
                    entry = entry,
                    index = index,
                    selectedId = state.selectedItemId,
                    onSelectItem = viewModel::selectItem,
                    onDelete = { id -> viewModel.deleteEntry(id) }
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryItem(
    entry: QuizHistoryEntry,
    index: Int,
    selectedId: Long?,
    onSelectItem: (id: Long?) -> Unit,
    onDelete: (id: Long) -> Unit,
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .wrapContentHeight()
    ) {
        DropdownMenu(
            expanded = selectedId == entry.id,
            onDismissRequest = { onSelectItem(null) },
            offset = DpOffset(x = 220.dp, y = 0.dp)
        ) {
            DropdownMenuItem(
                onClick = {
                    onDelete(entry.id)
                    onSelectItem(null)
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.delete_icon),
                        contentDescription = stringResource(R.string.delete_item)
                    )
                },
                text = { Text(stringResource(R.string.delete_item)) }
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    onClick = { },
                    onLongClick = { onSelectItem(entry.id) }
                ),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp,
                pressedElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Quiz ${index + 1}",
                        style = MaterialTheme.typography.titleLarge
                    )
                    ScoreStars(entry.correctAnswers, entry.totalQuestions)
                }
                Text(
                    "Дата: " +
                            SimpleDateFormat("dd.MM.yyyy HH:mm")
                                .format(Date(entry.dateTime))
                )
                Text(
                    "Сложность: ${entry.difficulty}",
                    modifier = Modifier.fillMaxWidth(1f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    SurfQuizTheme {
        HistoryItem(
            QuizHistoryEntry(222, 1234567890L, 4, 5, "medium"),
            index = 1,
            222,
            { id: Long? -> Unit },
            { id -> Unit }
        )
    }
}