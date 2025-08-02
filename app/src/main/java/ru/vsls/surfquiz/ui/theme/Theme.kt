package ru.vsls.surfquiz.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

// --- Новый ColorScheme на ваших цветах:
private val LightColorScheme = lightColorScheme(
    primary = IndigoBackground,           // основной брендовый цвет
    secondary = GoldRating,               // для акцентов
    background = IndigoBackground,       // общий фон
    surface = WhiteContainer,             // цвет контейнера
    onPrimary = Color.White,              // контраст к primary
    onSecondary = Color.White,            // контраст к secondary
    onBackground = Color.Black,           // контраст к background
    onSurface = Color.Black,              // контраст к surface
)

// --- Кастомные цвета (правильный, неправильный и пр.) ---
data class SurfQuizColors(
    val correct: Color,
    val wrong: Color,
    val rating: Color,
    val selected: Color,
    val inactive: Color,
)

val LocalSurfQuizColors = staticCompositionLocalOf {
    SurfQuizColors(
        correct = GreenCorrect,
        wrong = RedWrong,
        rating = GoldRating,
        selected = VioletSelected,
        inactive = GreyInactive
    )
}

@Composable
fun SurfQuizTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val surfQuizColors = SurfQuizColors(
        correct = GreenCorrect,
        wrong = RedWrong,
        rating = GoldRating,
        selected = VioletSelected,
        inactive = GreyInactive
    )
    androidx.compose.runtime.CompositionLocalProvider(
        LocalSurfQuizColors provides surfQuizColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}