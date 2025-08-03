package ru.vsls.surfquiz.presentation.screens.history

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.vsls.surfquiz.domain.usecase.DeleteQuizHistoryEntryUseCase
import ru.vsls.surfquiz.domain.usecase.GetQuizHistoryUseCase
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getQuizHistoryUseCase: GetQuizHistoryUseCase,
    private val deleteQuizHistoryUseCase: DeleteQuizHistoryEntryUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(HistoryUiState())
    val state: StateFlow<HistoryUiState> = _state.asStateFlow()

    fun loadHistory() {
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            try {
                val history = getQuizHistoryUseCase()
                _state.value = _state.value.copy(isLoading = false, history = history)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    fun selectItem(id: Long?) {
        _state.value = _state.value.copy(selectedItemId = id)
    }

    fun deleteEntry(id: Long) {
        viewModelScope.launch {
            deleteQuizHistoryUseCase(id)
            loadHistory()
        }
    }
}