package ru.vsls.surfquiz.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.vsls.surfquiz.ui.theme.SurfQuizTheme

@Composable
fun QuizScreen() {
    val viewModel: QuizViewModel = hiltViewModel()
    val uiState by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Добро пожаловать в DailyQuiz!", modifier = Modifier.padding(bottom = 16.dp))
        Button(onClick = { viewModel.loadQuiz() }) {
            Text("Загрузить квиз")
        }
        when {
            uiState.isLoading -> CircularProgressIndicator()
            uiState.error != null -> Text("Ошибка: ${uiState.error}")
            uiState.questions.isNotEmpty() -> Text("Получено вопросов: ${uiState.questions.size}")
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