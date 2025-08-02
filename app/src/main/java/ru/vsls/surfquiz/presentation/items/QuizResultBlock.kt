package ru.vsls.surfquiz.presentation.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.vsls.surfquiz.domain.model.Question
import ru.vsls.surfquiz.ui.theme.SurfQuizTheme
import ru.vsls.surfquiz.ui.theme.LocalSurfQuizColors

@Composable
fun QuizResultBlock(
    correct: Int,
    total: Int,
    questions: List<Question>,
    userAnswers: List<String>,
    onRestart: () -> Unit,
) {
    val quizColors = LocalSurfQuizColors.current
    Card(
        modifier = Modifier.padding(12.dp),
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
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                "Результат: $correct/$total",
                color = quizColors.rating,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            questions.zip(userAnswers).forEachIndexed { idx, (q, a) ->
                val correctMark = if (a == q.correctAnswer) "✔" else "✗"
                val color = if (a == q.correctAnswer) quizColors.correct else quizColors.wrong
                Text("${idx + 1}. ${q.question}", modifier = Modifier.padding(bottom = 4.dp))
                Text(
                    "Ваш ответ: $a $correctMark",
                    color = color,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            Button(onClick = onRestart, modifier = Modifier.padding(top = 24.dp)) {
                Text("Пройти ещё раз")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizResultBlockPreview() {
    SurfQuizTheme {
        QuizResultBlock(2, 3, listOf(), listOf(),) { }
    }
}