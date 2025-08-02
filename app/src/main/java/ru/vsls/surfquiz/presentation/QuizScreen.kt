package ru.vsls.surfquiz.presentation

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ru.vsls.surfquiz.presentation.items.QuizQuestionBlock
import ru.vsls.surfquiz.presentation.items.QuizResultBlock
import ru.vsls.surfquiz.ui.theme.SurfQuizTheme

@Composable
fun QuizScreen() {
    val viewModel: QuizViewModel = hiltViewModel()
    val uiState by viewModel.state.collectAsState()

    when {
        uiState.isLoading -> CircularProgressIndicator()
        uiState.error != null -> Text("Ошибка: ${uiState.error}", color = Color.Red)
        uiState.questions.isEmpty() -> Button(onClick = { viewModel.loadQuiz() }) { Text("Начать квиз") }
        uiState.quizFinished ->
            QuizResultBlock(
                correct = uiState.correctCount,
                total = uiState.questions.size,
                questions = uiState.questions,
                userAnswers = uiState.userAnswers,
                onRestart = { viewModel.loadQuiz() }
            )
        else -> {
            val qIdx = uiState.currentQuestionIndex
            val question = uiState.questions[qIdx]
            val answers = remember(question) {
                (question.incorrectAnswers + question.correctAnswer).shuffled()
            }
            QuizQuestionBlock(
                question = question.question,
                answers = answers,
                selectedAnswer = uiState.selectedAnswer,
                onSelectAnswer = { viewModel.selectAnswer(it) },
                currentIndex = qIdx,
                total = uiState.questions.size,
                onNext = { viewModel.onNextQuestion() },
                isNextEnabled = uiState.selectedAnswer != null,
                isLast = qIdx == uiState.questions.lastIndex
            )
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