package ru.vsls.surfquiz.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.vsls.surfquiz.domain.usecase.GetQuizzesUseCase
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuizzesUseCase: GetQuizzesUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(QuizUiState())
    val state: StateFlow<QuizUiState> = _state

    fun loadQuiz() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val questions = getQuizzesUseCase()
                _state.value = QuizUiState(questions = questions)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

    fun selectAnswer(answer: String) {
        _state.value = _state.value.copy(selectedAnswer = answer)
    }

    fun onNextQuestion() {
        val state = _state.value
        val updatedAnswers = state.userAnswers + (state.selectedAnswer ?: "")
        if (state.currentQuestionIndex < state.questions.lastIndex) {
            _state.value = state.copy(
                currentQuestionIndex = state.currentQuestionIndex + 1,
                selectedAnswer = null,
                userAnswers = updatedAnswers
            )
        } else {
            val correctCount =
                state.questions.zip(updatedAnswers).count { (q, a) -> q.correctAnswer == a }
            _state.value = state.copy(
                userAnswers = updatedAnswers,
                quizFinished = true,
                correctCount = correctCount
            )
        }
    }

}
