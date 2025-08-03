package ru.vsls.surfquiz.presentation.navigation

sealed class Screen(val route: String) {
    object Quiz : Screen("quiz")
    object History : Screen("history")
    object Details : Screen("details/{id}")
}
