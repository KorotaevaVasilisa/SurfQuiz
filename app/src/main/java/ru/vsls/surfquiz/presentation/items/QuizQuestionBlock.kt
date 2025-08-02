package ru.vsls.surfquiz.presentation.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.vsls.surfquiz.ui.theme.LocalSurfQuizColors
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.vsls.surfquiz.R
import ru.vsls.surfquiz.ui.theme.SurfQuizTheme

@Composable
fun QuizQuestionBlock(
    question: String,
    answers: List<String>,
    selectedAnswer: String?,
    onSelectAnswer: (String) -> Unit,
    currentIndex: Int,
    total: Int,
    isLast: Boolean,
    isInteractionBlocked: Boolean,
    isAnswerCorrect: Boolean?,
    isNextEnabled: Boolean,
    correctAnswer: String?, // новый параметр
    onNext: () -> Unit,
    onCheckAnswer: () -> Unit,
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
            modifier = Modifier
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
                modifier = Modifier.padding(bottom = 24.dp),
                textAlign = TextAlign.Center
            )
            answers.forEach { answer ->
                val isSelected = selectedAnswer == answer
                val isRight = correctAnswer == answer
                val colorItem: Color = when {
                    isSelected && isAnswerCorrect == true -> quizColors.correct
                    isSelected && isAnswerCorrect == false -> quizColors.wrong
                    !isSelected && isAnswerCorrect == false && isRight -> quizColors.correct
                    isSelected && isAnswerCorrect == null -> quizColors.selected
                    else -> quizColors.standart
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    RadioButton(
                        selected = isSelected,
                        onClick = { if (!isInteractionBlocked) onSelectAnswer(answer) },
                        enabled = !isInteractionBlocked,
                        colors = androidx.compose.material3.RadioButtonDefaults.colors(
                            selectedColor = colorItem,
                            disabledSelectedColor = colorItem,
                            disabledUnselectedColor = colorItem,
                        ),
                    )
                    Text(
                        text = answer,
                        color = colorItem,
                    )
                }
            }
            Button(
                onClick = onCheckAnswer,
                enabled = isNextEnabled && !isInteractionBlocked,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth()
            ) {
                Text(if (isLast) stringResource(R.string.complete_quiz) else stringResource(R.string.next_question))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QuizQuestionBlockPreview() {
    SurfQuizTheme {
        QuizQuestionBlock(
            question = "How long human can't breathe?",
            answers = listOf("1 minute", "5 minutes", "10 minutes"),
            selectedAnswer = "1 minute",
            onSelectAnswer = {},
            currentIndex = 0,
            total = 10,
            isLast = false,
            isInteractionBlocked = false,
            isAnswerCorrect = false,
            isNextEnabled = true,
            correctAnswer = "5 minutes",
            onNext = {},
            onCheckAnswer = {}
        )
    }
}