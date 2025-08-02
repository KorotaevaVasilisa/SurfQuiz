package ru.vsls.surfquiz.presentation.items

import android.R.attr.contentDescription
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.vsls.surfquiz.R
import ru.vsls.surfquiz.domain.model.Question
import ru.vsls.surfquiz.ui.theme.LocalSurfQuizColors
import ru.vsls.surfquiz.ui.theme.SurfQuizTheme

@Composable
fun ScoreStars(
    score: Int,
    max: Int,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        for (i in 1..max) {
            Image(
                painter = if (i <= score) painterResource(R.drawable.active_star) else painterResource(R.drawable.inactive_star),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

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
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            val desc = getResultDescription(correct, total)
            ScoreStars(
                score = correct,
                max = total,
                modifier = Modifier.padding(vertical = 12.dp)
            )
            Text(
                text = desc.shortScore,
                color = quizColors.rating,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = desc.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = desc.subtitle,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 20.dp),
                textAlign = TextAlign.Center
            )
//            questions.zip(userAnswers).forEachIndexed { idx, (q, a) ->
//                val correctMark = if (a == q.correctAnswer) "✔" else "✗"
//                val color = if (a == q.correctAnswer) quizColors.correct else quizColors.wrong
//                Text("${idx + 1}. ${q.question}", modifier = Modifier.padding(bottom = 4.dp))
//                Text(
//                    "Ваш ответ: $a $correctMark",
//                    color = color,
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//            }
            Button(onClick = onRestart, modifier = Modifier.padding(top = 24.dp)) {
                Text(stringResource(R.string.start_again))
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

data class ResultDescription(val shortScore: String, val title: String, val subtitle: String)

fun getResultDescription(score: Int, total: Int): ResultDescription {
    return when (score) {
        total -> ResultDescription(
            "$score из $total",
            "Идеально!",
            "$score/$total — вы ответили на всё правильно. Это блестящий результат!"
        )

        total - 1 -> ResultDescription(
            "$score из $total",
            "Почти идеально!",
            "$score/$total — очень близко к совершенству. Ещё один шаг!"
        )

        total - 2 -> ResultDescription(
            "$score из $total",
            "Хороший результат!",
            "$score/$total — вы на верном пути. Продолжайте тренироваться!"
        )

        2 -> ResultDescription(
            "$score из $total",
            "Есть над чем поработать",
            "$score/$total — не расстраивайтесь, попробуйте ещё раз!"
        )

        1 -> ResultDescription(
            "$score из $total",
            "Сложный вопрос?",
            "$score/$total — иногда просто не ваш день. Следующая попытка будет лучше!"
        )

        else -> ResultDescription(
            "$score из $total",
            "Бывает и так!",
            "$score/$total — не отчаивайтесь. Начните заново и удивите себя!"
        )
    }
}