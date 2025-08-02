package ru.vsls.surfquiz.presentation.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.vsls.surfquiz.ui.theme.SurfQuizTheme

@Composable
fun QuizQuestionBlock(
    question: String,
    answers: List<String>,
    selectedAnswer: String?,
    onSelectAnswer: (String) -> Unit,
    currentIndex: Int,
    total: Int,
    onNext: () -> Unit,
    isNextEnabled: Boolean,
    isLast: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Вопрос ${currentIndex + 1} из $total",
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = question,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        answers.forEach { answer ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                RadioButton(
                    selected = selectedAnswer == answer,
                    onClick = { onSelectAnswer(answer) }
                )
                Text(text = answer)
            }
        }
        Button(
            onClick = onNext,
            enabled = isNextEnabled,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        ) {
            Text(if (isLast) "Завершить" else "Далее")
        }
        Text(
            text = "Вернуться к предыдущим вопросам нельзя",
            color = Color.Gray,
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun QuizQuestionBlockPreview() {
    SurfQuizTheme {
        QuizQuestionBlock("How long human can't breathe?", listOf("1 minute", "5 minutes", "10 minutes"), null, {}, 0, 10, {}, true, false)
    }
}