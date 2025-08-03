package ru.vsls.surfquiz.presentation.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.vsls.surfquiz.R

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