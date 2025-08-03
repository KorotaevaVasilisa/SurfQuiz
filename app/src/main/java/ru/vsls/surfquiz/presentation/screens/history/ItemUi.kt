package ru.vsls.surfquiz.presentation.screens.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import ru.vsls.surfquiz.R
import ru.vsls.surfquiz.presentation.items.ScoreStars

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryItem(
    model: QuizHistoryUiModel,
    isDimmed: Boolean,
    isSelected: Boolean,
    onLongClick: (id: Long?) -> Unit,
    onDelete: () -> Unit,
    onNextScreen: ()->Unit
) {
    val cardColor =
        if (isDimmed) MaterialTheme.colorScheme.inverseSurface else MaterialTheme.colorScheme.surface

    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .wrapContentHeight()
    ) {
        if (isSelected) {
            DropdownMenu(
                expanded = true,
                onDismissRequest = { onLongClick(null) },
                offset = DpOffset(x = 220.dp, y = 0.dp)
            ) {
                DropdownMenuItem(
                    onClick = {
                        onDelete()
                        onLongClick(null)
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
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    onClick = { onNextScreen() },
                    onLongClick = { onLongClick(model.id) }
                ),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp,
                pressedElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = cardColor,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
        ) {
            ContentHistoryCard(
                model.title,
                model.correctAnswers,
                model.totalQuestions,
                model.formattedDate,
                model.difficulty
            )
        }
    }
}

@Composable
fun ContentHistoryCard(
    title: String,
    correctAnswers: Int,
    totalQuestions: Int,
    formattedDate: String,
    difficulty: String,
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
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
            ScoreStars(correctAnswers, totalQuestions)
        }
        Text("Дата: ${formattedDate}")
        Text(
            "Сложность: ${difficulty}",
            modifier = Modifier.fillMaxWidth(1f),
            textAlign = TextAlign.Center
        )
    }
}