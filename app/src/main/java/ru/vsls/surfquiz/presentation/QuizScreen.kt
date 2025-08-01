package ru.vsls.surfquiz.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.vsls.surfquiz.ui.theme.SurfQuizTheme

@Composable
fun QuizScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Добро пожаловать в DailyQuiz!", modifier = Modifier.padding(bottom = 16.dp))
        Button(onClick = {}) {
            Text("Начать викторину")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    SurfQuizTheme {
        QuizScreen()
    }
}